package com.example.inventariocqrs.domain;

import java.util.UUID;

public record ProductoId(String value) {
    public static ProductoId nuevo() {
        return new ProductoId(UUID.randomUUID().toString());
    }
}