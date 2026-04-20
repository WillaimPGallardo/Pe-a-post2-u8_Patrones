package com.example.inventariocqrs.query.repository;

import com.example.inventariocqrs.query.model.ProductoView;
import java.util.List;

public interface ProductoReadRepository {
    List<ProductoView> buscarTodos();
}