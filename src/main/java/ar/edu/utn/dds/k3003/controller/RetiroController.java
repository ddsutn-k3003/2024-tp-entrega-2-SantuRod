package ar.edu.utn.dds.k3003.controller;

import ar.edu.utn.dds.k3003.app.Fachada;
import ar.edu.utn.dds.k3003.facades.dtos.RetiroDTO;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.NoSuchElementException;

public class RetiroController {
    private final Fachada fachada;

    public RetiroController(Fachada fachada){
        this.fachada = fachada;
    }
    public void retirar(Context context){
        try{
            var retiroDTO = context.bodyAsClass(RetiroDTO.class);
            this.fachada.retirar(retiroDTO);
            context.result("Vianda retirada correctamente");
            context.status(HttpStatus.OK);
        }
        catch (NoSuchElementException ex){
            context.result("Error de solicitud");
            context.status(HttpStatus.BAD_REQUEST);
        }
    }
}
