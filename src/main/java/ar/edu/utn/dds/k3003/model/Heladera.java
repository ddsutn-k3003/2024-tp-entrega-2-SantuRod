package ar.edu.utn.dds.k3003.model;

import ar.edu.utn.dds.k3003.facades.dtos.ViandaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class Heladera {
    private Integer id;
    private String nombre;
    private ArrayList<ViandaDTO> viandas;
    private ArrayList<Temperatura> temperaturas;

    public Heladera(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.viandas = new ArrayList<>();
        this.temperaturas = new ArrayList<>();
    }

    public void addVianda(ViandaDTO vianda){
        this.viandas.add(vianda);
    }
    public void removeVianda(String codigoQR){
        this.viandas.removeIf(x -> codigoQR.equals(x.getCodigoQR()));
    }
    public void addTemperatura(Temperatura temperatura){
        this.temperaturas.add(temperatura);
        temperaturas.sort((x,y) -> y.getFechaMedicion().compareTo(x.getFechaMedicion()));
    }
}