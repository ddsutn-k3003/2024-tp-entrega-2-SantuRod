package ar.edu.utn.dds.k3003.app;

import ar.edu.utn.dds.k3003.controller.DepositoController;
import ar.edu.utn.dds.k3003.controller.HeladeraController;
import ar.edu.utn.dds.k3003.controller.RetiroController;
import ar.edu.utn.dds.k3003.controller.TemperaturaController;
import io.javalin.Javalin;
public class WebApp {
    public static void main(String[] args){
        var env = System.getenv();
        var fachada = new Fachada();

        var port = Integer.parseInt(env.getOrDefault("PORT","8080"));

        var app = Javalin.create().start(port);

        var heladeraController = new HeladeraController(fachada);
        var depositoController = new DepositoController(fachada);
        var retiroController = new RetiroController(fachada);
        var temperaturaController = new TemperaturaController(fachada);

        app.post("/heladeras", heladeraController::agregar);
        app.get("/heladeras/{heladeraId}", heladeraController::obtener);
        app.post("/depositos", depositoController::depositar);
        app.post("/retiros", retiroController::retirar);
        app.post("/temperaturas", temperaturaController::agregar);
        app.get("/heladeras/{heladeraId}/temperaturas", heladeraController::obtenerTemperaturas);
    }
}
