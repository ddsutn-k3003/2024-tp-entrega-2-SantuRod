package ar.edi.itn.dds.k3003.model;

import ar.edu.utn.dds.k3003.app.Fachada;
import ar.edu.utn.dds.k3003.facades.FachadaViandas;
import ar.edu.utn.dds.k3003.facades.dtos.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@ExtendWith({MockitoExtension.class})
public class FachadaTest {
    private Integer HELADERA_ID;
    Fachada fachada = new Fachada();
    @Mock
    FachadaViandas viandas;

    public FachadaTest() {
    }

    @BeforeEach
    void setUp(){
        try {
            HeladeraDTO unaHeladera = this.fachada.agregar(new HeladeraDTO("Heladera1"));
            this.fachada.setViandasProxy(this.viandas);
            this.HELADERA_ID = unaHeladera.getId();
        } catch (Throwable ex){
            throw ex;
        }
    }

    @Test
    @DisplayName("Depositar 3 viandas en una misma heladera, agregando una heladera adicional.")
    void testDepositarViandas() {
        Mockito.when(this.viandas.buscarXQR("QR1")).thenReturn(new ViandaDTO("QR1", LocalDateTime.now(), EstadoViandaEnum.PREPARADA, 1L, HELADERA_ID));
        Mockito.when(this.viandas.buscarXQR("QR2")).thenReturn(new ViandaDTO("QR2", LocalDateTime.now(), EstadoViandaEnum.PREPARADA, 1L, HELADERA_ID));
        Mockito.when(this.viandas.buscarXQR("QR3")).thenReturn(new ViandaDTO("QR3", LocalDateTime.now(), EstadoViandaEnum.PREPARADA, 1L, HELADERA_ID));
        Mockito.when(this.viandas.buscarXQR("QR4")).thenReturn(new ViandaDTO("QR4", LocalDateTime.now(), EstadoViandaEnum.PREPARADA, 1L, 21));
        HeladeraDTO otraHeladera = this.fachada.agregar(new HeladeraDTO("Heladera2"));
        Integer otraHeladeraId = otraHeladera.getId();
        fachada.depositar(HELADERA_ID, "QR1");
        fachada.depositar(HELADERA_ID, "QR2");
        fachada.depositar(HELADERA_ID, "QR3");
        fachada.depositar(otraHeladeraId, "QR4");
        Assertions.assertEquals(3, fachada.cantidadViandas(HELADERA_ID), "Las viandas no se agregaron correctamente");
    }
    @Test
    @DisplayName("Se retiran 2 de las 3 viandas ingresadas a la heladera 20")
    void testRetirarViandas(){
        Mockito.when(this.viandas.buscarXQR("QR1")).thenReturn(new ViandaDTO("QR1", LocalDateTime.now(), EstadoViandaEnum.PREPARADA, 1L, HELADERA_ID));
        Mockito.when(this.viandas.buscarXQR("QR2")).thenReturn(new ViandaDTO("QR2", LocalDateTime.now(), EstadoViandaEnum.PREPARADA, 1L, HELADERA_ID));
        Mockito.when(this.viandas.buscarXQR("QR3")).thenReturn(new ViandaDTO("QR3", LocalDateTime.now(), EstadoViandaEnum.PREPARADA, 1L, HELADERA_ID));
        HeladeraDTO otraHeladera = this.fachada.agregar(new HeladeraDTO("Heladera2"));
        Integer otraHeladeraId = otraHeladera.getId();
        fachada.depositar(HELADERA_ID, "QR1");
        fachada.depositar(otraHeladeraId, "QR1");
        fachada.depositar(HELADERA_ID, "QR2");
        fachada.depositar(HELADERA_ID, "QR3");
        fachada.retirar(new RetiroDTO("QR2","asd",HELADERA_ID ));
        fachada.retirar(new RetiroDTO("QR3","asd",HELADERA_ID ));
        fachada.retirar(new RetiroDTO("QR1","asd",otraHeladeraId ));
        Assertions.assertEquals(1, fachada.cantidadViandas(HELADERA_ID), "Las viandas no se retiraron correctamente");
    }
    @Test
    @DisplayName("Se intenta depositar una vianda de una heladera inexistente")
    void testDepositarEnHeladeraInexistente(){
        Mockito.when(this.viandas.buscarXQR("QR1")).thenReturn(new ViandaDTO("QR1", LocalDateTime.now(), EstadoViandaEnum.PREPARADA, 1L, HELADERA_ID));
        Exception ex = Assertions.assertThrows(NoSuchElementException.class, () -> fachada.depositar(21,"QR1"));
        Assertions.assertEquals("No hay heladera de id: 21", ex.getMessage(), "Se esperaba una excepción que no encontrara la heladera indicada");
    }
    @Test
    @DisplayName("Se guardan y devuelven las temperaturas en orden correcto")
    void testTemperaturas(){
        fachada.temperatura(new TemperaturaDTO(4,HELADERA_ID, LocalDateTime.now()));
        fachada.temperatura(new TemperaturaDTO(5,HELADERA_ID, LocalDateTime.now().plusHours(10L)));
        fachada.temperatura(new TemperaturaDTO(8,HELADERA_ID, LocalDateTime.now()));
        List<TemperaturaDTO> temperaturaDTOS = fachada.obtenerTemperaturas(HELADERA_ID);
        Assertions.assertEquals(3, temperaturaDTOS.size(), "Las temperaturas no se guardaron correctamente");
        Assertions.assertEquals(5, temperaturaDTOS.get(0).getTemperatura(),"Las temperaturas no se guardaron correctamente");
    }
}