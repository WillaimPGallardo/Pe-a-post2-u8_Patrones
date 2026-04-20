package com.example.inventariocqrs.domain;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(ProductoId id, int stock, int solicitado) {
        super("Stock insuficiente. Disponible: " + stock + ", solicitado: " + solicitado);
    }
}