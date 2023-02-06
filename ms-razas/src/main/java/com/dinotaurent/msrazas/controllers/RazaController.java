package com.dinotaurent.msrazas.controllers;

import com.dinotaurent.mscommons.controllers.CommonsController;
import com.dinotaurent.mscommonsmascotasrazas.models.entity.Mascota;
import com.dinotaurent.mscommonsmascotasrazas.models.entity.Raza;
import com.dinotaurent.mscommonsmascotasrazas.models.entity.RazaMascotas;
import com.dinotaurent.msrazas.models.services.IRazaService;
import jakarta.validation.Valid;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class RazaController extends CommonsController<Raza, IRazaService> {

    @GetMapping("/")
    @Override
    public ResponseEntity<?> listar(){
        List<Raza> razas = service.findAll().stream().peek(r -> r.getRazaMascotas().forEach(rm -> {
            Mascota mascota = new Mascota();
            mascota.setId(rm.getId());
            r.addMascota(mascota);
        })).toList();
        return ResponseEntity.ok(razas);
    }

    @GetMapping("/pagina")
    @Override
    public ResponseEntity<?> listarXpaginas(Pageable pageable){
        Page<Raza> razas = service.findAll(pageable).map(r -> {
            r.getRazaMascotas().forEach(rm -> {
                Mascota mascota = new Mascota();
                mascota.setId(rm.getId());
                r.addMascota(mascota);
            });
            return r;
        });
        return ResponseEntity.ok(razas);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<?> listarXId(@PathVariable Long id){
        Optional<Raza> o = service.findById(id);
        if (o.isPresent()){
            Raza razaBd = o.get();
            if(!razaBd.getRazaMascotas().isEmpty()){
                List<Long> ids = razaBd.getRazaMascotas().stream().map(RazaMascotas::getMascotaId).toList();
                List<Mascota> mascotas = service.buscarMascotasXIds(ids);
                razaBd.setMascotas(mascotas);
            }
            return ResponseEntity.ok(razaBd);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar-raza-x-nombre/{termino}")
    public ResponseEntity<?> buscarRazaXNombre(@PathVariable String termino){
        return  ResponseEntity.ok(service.findByNombreContaining(termino));
    }


    @GetMapping("/ver-foto/{id}")
    public ResponseEntity<?> verFoto(@PathVariable Long id){
        Optional<Raza> o = service.findById(id);

        if(o.isPresent() && o.get().getFoto() != null){
            Resource imagen = new ByteArrayResource(o.get().getFoto());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/registrar-con-foto")
    public ResponseEntity<?> registrarConFoto(@Valid Raza raza, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
        if(!archivo.isEmpty()){
            raza.setFoto(archivo.getBytes());
        }
        return super.crear(raza, result);
    }


    @PutMapping("/actualizar-con-foto/{id}")
    public ResponseEntity<?> actualizarConFoto(@PathVariable Long id, @Valid Raza raza, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
        Optional<Raza> o = service.findById(id);

        if (o.isPresent()){
            Raza razaBd = o.get();
            razaBd.setNombre(raza.getNombre());
            if(!archivo.isEmpty()){
                razaBd.setFoto(archivo.getBytes());
            }

            if (result.hasErrors()){
                return mostrarErrores(result);
            }else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(razaBd));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Raza raza, BindingResult result) {
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

    @PutMapping("/{id}/asignar-mascota")
    public ResponseEntity<?> asignarMascota(@PathVariable Long id, @RequestBody List<Mascota> mascotas) {
        Optional<Raza> o = service.findById(id);

        if (o.isPresent()) {
            Raza razaBd = o.get();
            mascotas.forEach(m -> {
                RazaMascotas razaMascotas = new RazaMascotas();
                razaMascotas.setMascotaId(m.getId());
                razaMascotas.setRaza(razaBd);
                razaBd.addRazaMascotas(razaMascotas);
            });
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(razaBd));
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}/remover-mascota")
    public ResponseEntity<?> removerMascota(@PathVariable Long id, @RequestBody Mascota mascota) {
        Optional<Raza> o = service.findById(id);

        if (o.isPresent()) {
            Raza razaBd = o.get();
            RazaMascotas razaMascotas = new RazaMascotas();
            razaMascotas.setMascotaId(mascota.getId());
            razaBd.removeRazaMascotas(razaMascotas);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(razaBd));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/asignar-foto")

    @DeleteMapping("/eliminar-razaMascotas/{mascotaId}")
    public ResponseEntity<?> eliminarRazaMascotas(@PathVariable Long mascotaId){
        service.eliminarRazaMascotasXId(mascotaId);
        return ResponseEntity.noContent().build();
    }


}
