package com.example.inventariocqrs.command.repository;

import com.example.inventariocqrs.domain.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductoWriteRepositoryImpl implements ProductoWriteRepository {

    private final Map<String, Producto> db = new HashMap<>();

    public void guardar(Producto p) {
        db.put(p.getId().toString(), p);
    }

    public Optional<Producto> buscarPorId(ProductoId id) {
        return Optional.ofNullable(db.get(id.toString()));
    }

    public Collection<Producto> obtenerTodos() {
        return db.values();
    }
}