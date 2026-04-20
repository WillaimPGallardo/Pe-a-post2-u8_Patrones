package com.example.inventariocqrs.adapter.web;

import com.example.inventariocqrs.command.*;
import com.example.inventariocqrs.command.handler.*;
import com.example.inventariocqrs.query.handler.*;
import com.example.inventariocqrs.query.model.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.*;

@RestController
@RequestMapping("/api/inventario")
public class ProductoController {

    private final AgregarProductoHandler agregar;
    private final ActualizarStockHandler stock;
    private final ListarProductosQueryHandler listar;

    public ProductoController(
            AgregarProductoHandler agregar,
            ActualizarStockHandler stock,
            ListarProductosQueryHandler listar) {
        this.agregar = agregar;
        this.stock = stock;
        this.listar = listar;
    }

    @PostMapping("/productos")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String,String> crear(@RequestBody AgregarProductoCommand cmd) {
        return Map.of("id", agregar.handle(cmd).toString());
    }

    @PatchMapping("/productos/{id}/stock")
    public Map<String,String> actualizar(
            @PathVariable String id,
            @RequestBody ActualizarStockCommand cmd) {

        return Map.of("mensaje",
                stock.handle(new ActualizarStockCommand(id, cmd.delta(), cmd.motivo())));
    }

    @GetMapping("/productos")
    public List<ProductoView> listar(@RequestParam(defaultValue = "false") boolean soloDisponibles) {
        return listar.handle(soloDisponibles);
    }
}