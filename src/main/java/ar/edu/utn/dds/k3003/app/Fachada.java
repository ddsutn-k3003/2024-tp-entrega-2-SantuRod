package ar.edu.utn.dds.k3003.app;

import ar.edu.utn.dds.k3003.facades.FachadaViandas;
import ar.edu.utn.dds.k3003.facades.dtos.*;
import ar.edu.utn.dds.k3003.model.Heladera;
import ar.edu.utn.dds.k3003.model.Temperatura;
import ar.edu.utn.dds.k3003.repositories.HeladeraMapper;
import ar.edu.utn.dds.k3003.repositories.HeladeraRepository;
import ar.edu.utn.dds.k3003.repositories.TemperaturaMapper;

import java.util.List;
import java.util.NoSuchElementException;

public class Fachada implements ar.edu.utn.dds.k3003.facades.FachadaHeladeras {

    private final HeladeraRepository heladeraRepository;
    private final HeladeraMapper heladeraMapper;
    private final TemperaturaMapper temperaturaMapper;
    private FachadaViandas fachadaViandas;
    public Fachada() {
        this.heladeraRepository = new HeladeraRepository();
        this.heladeraMapper = new HeladeraMapper();
        this.temperaturaMapper = new TemperaturaMapper();
    }

    @Override
    public HeladeraDTO agregar(HeladeraDTO heladeraDTO) {
        Heladera heladera = new Heladera(heladeraDTO.getId(), heladeraDTO.getNombre());
        heladera = this.heladeraRepository.save(heladera);
        return heladeraMapper.map(heladera);
    }

    @Override
    public void depositar(Integer integer, String s) throws NoSuchElementException { // ID HELADERA, QR VIANDA
        ViandaDTO viandaDTO = fachadaViandas.buscarXQR(s);
        Heladera heladera = this.heladeraRepository.findById(integer);
        heladera.addVianda(viandaDTO);
        fachadaViandas.modificarEstado(viandaDTO.getCodigoQR(), EstadoViandaEnum.DEPOSITADA);
    }

    @Override
    public Integer cantidadViandas(Integer integer) throws NoSuchElementException { // ID HELADERA
        Heladera heladera = this.heladeraRepository.findById(integer);
        return heladera.getViandas().size();
    }

    @Override
    public void retirar(RetiroDTO retiroDTO) throws NoSuchElementException {
        String qrVianda = retiroDTO.getQrVianda();
        Integer heladeraId = retiroDTO.getHeladeraId();
        Heladera heladera = this.heladeraRepository.findById(heladeraId);
        heladera.removeVianda(qrVianda);
        fachadaViandas.modificarEstado(qrVianda, EstadoViandaEnum.RETIRADA);
    }

    @Override
    public void temperatura(TemperaturaDTO temperaturaDTO) {
        Temperatura temperatura = new Temperatura(temperaturaDTO.getTemperatura(), temperaturaDTO.getHeladeraId(), temperaturaDTO.getFechaMedicion());
        Integer heladeraId = temperaturaDTO.getHeladeraId();
        Heladera heladera = this.heladeraRepository.findById(heladeraId);
        heladera.addTemperatura(temperatura);
    }

    @Override
    public List<TemperaturaDTO> obtenerTemperaturas(Integer integer) {
        Heladera heladera = this.heladeraRepository.findById(integer);
        return temperaturaMapper.map(heladera.getTemperaturas());
    }

    @Override
    public void setViandasProxy(FachadaViandas fachadaViandas) {
        this.fachadaViandas = fachadaViandas;
    }
    public HeladeraDTO obtenerHeladera(int heladeraId){
        Heladera heladera = this.heladeraRepository.findById(heladeraId);
        return heladeraMapper.map(heladera);
    }
}