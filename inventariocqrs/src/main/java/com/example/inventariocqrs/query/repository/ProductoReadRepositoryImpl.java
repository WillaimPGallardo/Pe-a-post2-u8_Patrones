package com.example.inventariocqrs.query.repository;

import com.example.inventariocqrs.command.repository.ProductoWriteRepositoryImpl;
import com.example.inventariocqrs.query.model.ProductoView;

import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;
import java.util.List;

@Repository
public class ProductoReadRepositoryImpl implements ProductoReadRepository {

    private final ProductoWriteRepositoryImpl repo;

    public ProductoReadRepositoryImpl(ProductoWriteRepositoryImpl repo) {
        this.repo = repo;
    }

    public List<ProductoView> buscarTodos() {
        return repo.obtenerTodos().stream()
                .map(p -> new ProductoView(
                        p.getId().toString(),
                        p.getNombre(),
                        p.getCategoria(),
                        p.getPrecioUnitario(),
                        p.getStockDisponible(),
                        ProductoView.calcularEstado(p.getStockDisponible())
                ))
                .collect(Collectors.toList());
    }
}