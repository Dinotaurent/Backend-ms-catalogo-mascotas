package com.dinotaurent.msrazas.controllers;

import com.dinotaurent.mscommons.controllers.CommonsController;
import com.dinotaurent.mscommonsmascotasrazas.models.entity.Raza;
import com.dinotaurent.msrazas.models.services.IRazaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RazaController extends CommonsController<Raza, IRazaService> {

    @PutMapping("/{id}")
    ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Raza raza, BindingResult result) {
        Optional<Raza> o = service.findById(id);

        if (o.isPresent()) {
            Raza razaBd = o.get();
            razaBd.setNombre(raza.getNombre());
            if (result.hasErrors()) {
                mostrarErrores(result);
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(razaBd));
            }
        }
        return ResponseEntity.notFound().build();
    }
}
