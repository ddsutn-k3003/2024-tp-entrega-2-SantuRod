package ar.edu.utn.dds.k3003.controller;

import ar.edu.utn.dds.k3003.app.Fachada;
import ar.edu.utn.dds.k3003.request.DepositoRequest;
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
            var depositoRequest = context.bodyAsClass(DepositoRequest.class);
            this.fachada.depositar(depositoRequest.getIdHeladera(), depositoRequest.getQrVianda());
            context.result("Vianda depositada correctamente");
            context.status(HttpStatus.OK);
        }
        catch (NoSuchElementException ex){
            context.result("Error de solicitud");
            context.status(HttpStatus.BAD_REQUEST);
        }
    }
}
