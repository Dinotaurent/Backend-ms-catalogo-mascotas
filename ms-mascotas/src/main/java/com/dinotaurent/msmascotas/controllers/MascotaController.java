package com.dinotaurent.msmascotas.controllers;

import com.dinotaurent.mscommons.controllers.CommonsController;
import com.dinotaurent.mscommonsmascotasrazas.models.entity.Mascota;
import com.dinotaurent.msmascotas.models.services.IMascotaService;
import jakarta.validation.Valid;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class MascotaController extends CommonsController<Mascota, IMascotaService> {


    @GetMapping("/buscar-mascotas-x-ids")
    public ResponseEntity<?> buscarMascotasXIds(@RequestParam List<Long> ids){
        return ResponseEntity.ok(service.findAllByIds(ids));
    }

    @GetMapping("/buscar-mascota-x-nombre/{termino}")
    public ResponseEntity<?> buscarMascotaXNombre(@PathVariable String termino){
        return ResponseEntity.ok(service.findByNombreContaining(termino));
    }

    @GetMapping("/ver-foto/{id}")
    public ResponseEntity<?> verFoto(@PathVariable Long id){
        Optional<Mascota> o = service.findById(id);

        if(o.isPresent() && o.get().getFoto() != null){
            Resource imagen = new ByteArrayResource(o.get().getFoto());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/registrar-con-foto")
    public ResponseEntity<?> registrarConFoto(@Valid Mascota mascota, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
        if(!archivo.isEmpty()){
            mascota.setFoto(archivo.getBytes());
        }
        return super.crear(mascota, result);
    }


    @PutMapping("/actualizar-con-foto/{id}")
    public ResponseEntity<?> actualizarConFoto(@PathVariable Long id, @Valid Mascota mascota, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
        Optional<Mascota> o = service.findById(id);

        if (o.isPresent()){
            Mascota mascotaBd = o.get();
            mascotaBd.setNombre(mascota.getNombre());
            mascotaBd.setEdad(mascota.getEdad());
            if(!archivo.isEmpty()){
                mascotaBd.setFoto(archivo.getBytes());
            }

            if (result.hasErrors()){
                return mostrarErrores(result);
            }else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(mascotaBd));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Mascota mascota, BindingResult result){
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
