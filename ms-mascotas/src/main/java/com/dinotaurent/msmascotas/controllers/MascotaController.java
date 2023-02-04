package com.dinotaurent.msmascotas.controllers;

import com.dinotaurent.mscommons.controllers.CommonsController;
import com.dinotaurent.mscommonsmascotasrazas.models.entity.Mascota;
import com.dinotaurent.msmascotas.models.services.IMascotaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MascotaController extends CommonsController<Mascota, IMascotaService> {

    @PutMapping("/{id}")
    ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Mascota mascota, BindingResult result){
        Optional<Mascota> o = service.findById(id);

        if(o.isPresent()){
            Mascota mascotaBd = o.get();

            mascotaBd.setNombre(mascota.getNombre());
            mascotaBd.setEdad(mascota.getEdad());
            if(result.hasErrors()){
                mostrarErrores(result);
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(mascotaBd));
            }
        }
        return ResponseEntity.notFound().build();
    }

}
