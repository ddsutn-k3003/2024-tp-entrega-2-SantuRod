package ar.edu.utn.dds.k3003.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DepositoRequest {
    private Integer idHeladera;
    private String qrVianda;
}
