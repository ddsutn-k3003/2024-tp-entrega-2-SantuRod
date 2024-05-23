package ar.edu.utn.dds.k3003.controller;

import ar.edu.utn.dds.k3003.request.DepositoRequest;
import ar.edu.utn.dds.k3003.app.Fachada;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.NoSuchElementException;

public class DepositoController {
    private final Fachada fachada;

    public DepositoController(Fachada fachada){
        this.fachada = fachada;
    }
    public void depositar(Context context){
        try{
            var depositoRequest = context.bodyAsClass(DepositoRequest.class);
            this.fachada.depositar(depositoRequest.getHeladeraId(), depositoRequest.getQrVianda());
            context.result("Vianda depositada correctamente");
            context.status(HttpStatus.OK);
        }
        catch (NoSuchElementException ex){
            context.result("Error de solicitud");
            context.status(HttpStatus.BAD_REQUEST);
        }
    }
}
