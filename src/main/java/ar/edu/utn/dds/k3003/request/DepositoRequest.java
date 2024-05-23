package ar.edu.utn.dds.k3003.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepositoRequest {
    private Integer heladeraId;
    private String qrVianda;
}
