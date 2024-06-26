package ar.edu.utn.dds.k3003.model;

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
    private Integer cantidadViandas;
    private ArrayList<Temperatura> temperaturas;

    public Heladera(Integer id, String nombre, Integer cantidadViandas) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadViandas = cantidadViandas;
        this.temperaturas = new ArrayList<>();
    }
    public void addTemperatura(Temperatura temperatura){
        this.temperaturas.add(temperatura);
        temperaturas.sort((x,y) -> y.getFechaMedicion().compareTo(x.getFechaMedicion()));
    }
    public void addVianda(){
        cantidadViandas++;
    }
    public void removeVianda(){
        cantidadViandas--;
    }
}