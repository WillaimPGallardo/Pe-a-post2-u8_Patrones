package com.example.inventariocqrs.command.handler;

import com.example.inventariocqrs.command.*;
import com.example.inventariocqrs.domain.*;
import com.example.inventariocqrs.command.repository.*;

import org.springframework.stereotype.Component;

@Component
public class AgregarProductoHandler {

    private final ProductoWriteRepository repo;

    public AgregarProductoHandler(ProductoWriteRepository repo) {
        this.repo = repo;
    }

    public ProductoId handle(AgregarProductoCommand cmd) {

        Producto p = new Producto(
                ProductoId.nuevo(),
                cmd.nombre(),
                cmd.categoria(),
                cmd.precioUnitario(),
                cmd.stockInicial()
        );

        repo.guardar(p);
        return p.getId();
    }
}