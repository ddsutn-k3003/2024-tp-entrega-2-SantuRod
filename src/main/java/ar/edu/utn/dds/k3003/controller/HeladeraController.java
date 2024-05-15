package ar.edu.utn.dds.k3003.controller;

import ar.edu.utn.dds.k3003.app.Fachada;
import ar.edu.utn.dds.k3003.facades.dtos.HeladeraDTO;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.NoSuchElementException;

public class HeladeraController {

    private final Fachada fachada;

    public HeladeraController(Fachada fachada){
        this.fachada = fachada;
    }

    public void agregar(Context context){
        try{
            var heladeraDTO = context.bodyAsClass(HeladeraDTO.class);
            var heladeraDTOrta = this.fachada.agregar(heladeraDTO);
            context.json(heladeraDTOrta);
            context.status(HttpStatus.OK);
        }
        catch (NoSuchElementException ex){
            context.result(ex.getLocalizedMessage());
            context.status(HttpStatus.BAD_REQUEST);
        }
    }

    public void obtener(Context context){
        try{
            String heladeraId = context.pathParam("heladeraId");
            Integer heladeraIdInt = Integer.parseInt(heladeraId);
            var heladeraDTO = this.fachada.obtenerHeladera(heladeraIdInt);
            context.json(heladeraDTO);
            context.status(HttpStatus.OK);
        }
        catch (NoSuchElementException ex){
            context.result(ex.getLocalizedMessage());
            context.status(HttpStatus.NOT_FOUND);
        }
    }
    public void obtenerTemperaturas (Context context){
        try{
            String heladeraId = context.pathParam("heladeraId");
            Integer heladeraIdInt = Integer.parseInt(heladeraId);
            var temperaturas = this.fachada.obtenerTemperaturas(heladeraIdInt);
            context.json(temperaturas);
            context.status(HttpStatus.OK);
        }
        catch (NoSuchElementException ex){
            context.result(ex.getLocalizedMessage());
            context.status(HttpStatus.NOT_FOUND);
        }
    }
}
