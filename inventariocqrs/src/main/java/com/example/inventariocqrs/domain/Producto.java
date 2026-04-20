package com.example.inventariocqrs.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Producto {

    private final ProductoId id;
    private String nombre;
    private String categoria;
    private BigDecimal precioUnitario;
    private int stockDisponible;

    public Producto(ProductoId id, String nombre, String categoria,
                    BigDecimal precio, int stockInicial) {

        this.id = Objects.requireNonNull(id);

        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("Nombre obligatorio");

        if (precio.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Precio inválido");

        this.nombre = nombre;
        this.categoria = categoria;
        this.precioUnitario = precio;
        this.stockDisponible = Math.max(0, stockInicial);
    }

    public void incrementarStock(int unidades) {
        if (unidades <= 0) throw new IllegalArgumentException();
        stockDisponible += unidades;
    }

    public void reducirStock(int unidades) {
        if (unidades > stockDisponible)
            throw new StockInsuficienteException(id, stockDisponible, unidades);
        stockDisponible -= unidades;
    }

    public ProductoId getId() { return id; }
    public int getStockDisponible() { return stockDisponible; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
}