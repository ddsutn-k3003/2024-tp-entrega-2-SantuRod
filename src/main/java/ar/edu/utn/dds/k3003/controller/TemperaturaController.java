package ar.edu.utn.dds.k3003.controller;

import ar.edu.utn.dds.k3003.app.Fachada;
import ar.edu.utn.dds.k3003.facades.dtos.TemperaturaDTO;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.NoSuchElementException;

public class TemperaturaController {
    private final Fachada fachada;

    public TemperaturaController(Fachada fachada){
        this.fachada = fachada;
    }

    public void agregar(Context context){
        try{
            var temperaturaDTO = context.bodyAsClass(TemperaturaDTO.class);
            this.fachada.temperatura(temperaturaDTO);
            context.result("Temperatura asignada correctamente");
            context.status(HttpStatus.OK);
        }
        catch (Exception ex){
            context.result("Error de solicitud");
            context.status(HttpStatus.BAD_REQUEST);
        }
    }
}
