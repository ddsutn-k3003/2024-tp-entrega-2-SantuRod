package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.facades.dtos.TemperaturaDTO;
import ar.edu.utn.dds.k3003.model.Temperatura;

import java.util.List;
import java.util.stream.Collectors;

public class TemperaturaMapper {
    public List<TemperaturaDTO> map (List<Temperatura> temperaturas){
        return temperaturas.stream().map(x -> new TemperaturaDTO(x.getTemperatura(),x.getHeladeraId(), x.getFechaMedicion())).collect(Collectors.toList());
    }
}