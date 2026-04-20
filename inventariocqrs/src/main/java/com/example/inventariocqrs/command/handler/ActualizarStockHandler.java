package com.example.inventariocqrs.command.handler;

import com.example.inventariocqrs.command.*;
import com.example.inventariocqrs.domain.*;
import com.example.inventariocqrs.command.repository.*;

import org.springframework.stereotype.Component;

@Component
public class ActualizarStockHandler {

    private final ProductoWriteRepository repo;

    public ActualizarStockHandler(ProductoWriteRepository repo) {
        this.repo = repo;
    }

    public String handle(ActualizarStockCommand cmd) {

        Producto p = repo.buscarPorId(new ProductoId(cmd.productoId()))
                .orElseThrow();

        if (cmd.delta() > 0)
            p.incrementarStock(cmd.delta());
        else
            p.reducirStock(Math.abs(cmd.delta()));

        repo.guardar(p);

        return "Nuevo stock: " + p.getStockDisponible();
    }
}