package com.dinotaurent.mscommons.controllers;

import com.dinotaurent.mscommons.models.service.ICommonsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

public class CommonsController<E, S extends ICommonsService<E>> {

    @Autowired
    protected S service;

    @GetMapping("/")
    ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/pagina")
    ResponseEntity<?> listarXpaginas(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    ResponseEntity<?> listarXId(@PathVariable Long id) {
        Optional<E> o = service.findById(id);
        if (o.isPresent()) {
            return ResponseEntity.ok(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    ResponseEntity<?> crear(@Valid @RequestBody E entity, BindingResult result) {
        return result.hasErrors()
                ? mostrarErrores(result)
                : ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
    }


    @DeleteMapping("/{id}")
    ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<E> o = service.findById(id);
        if (o.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    protected ResponseEntity<?> mostrarErrores(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
