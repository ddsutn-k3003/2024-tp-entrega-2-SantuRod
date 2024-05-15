package ar.edu.utn.dds.k3003.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Temperatura {
    private Integer temperatura;
    private Integer heladeraId;
    private LocalDateTime fechaMedicion;

}