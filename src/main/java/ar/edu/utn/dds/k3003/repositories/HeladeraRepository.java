package ar.edu.utn.dds.k3003.repositories;

import ar.edu.utn.dds.k3003.model.Heladera;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class HeladeraRepository {
    private static AtomicLong seqId = new AtomicLong();
    private Collection<Heladera> heladeras;

    public HeladeraRepository(){
        this.heladeras = new ArrayList<>();
    }

    public Heladera save(Heladera heladera){
        if(Objects.isNull(heladera.getCantidadViandas())){
            heladera.setCantidadViandas(0);
        }
        if(Objects.isNull(heladera.getId())) {
            heladera.setId((int) seqId.getAndIncrement());
            this.heladeras.add(heladera);
        }
        return heladera;
    }

    public Heladera findById(Integer id){
        Optional<Heladera> first = this.heladeras.stream().filter(x -> x.getId().equals(id)).findFirst();
        return first.orElseThrow(() -> new NoSuchElementException(
                String.format("No hay heladera de id: %s", id)
        ));
    }
}