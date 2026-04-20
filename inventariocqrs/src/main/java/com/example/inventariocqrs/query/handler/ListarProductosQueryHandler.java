package com.example.inventariocqrs.query.handler;

import com.example.inventariocqrs.query.repository.*;
import com.example.inventariocqrs.query.model.*;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListarProductosQueryHandler {

    private final ProductoReadRepository repo;

    public ListarProductosQueryHandler(ProductoReadRepository repo) {
        this.repo = repo;
    }

    public List<ProductoView> handle(boolean soloDisponibles) {
        return repo.buscarTodos().stream()
                .filter(p -> soloDisponibles ? p.stockDisponible() > 0 : true)
                .toList();
    }
}