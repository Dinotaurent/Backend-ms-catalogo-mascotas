package com.dinotaurent.msrazas.clients;

import com.dinotaurent.mscommonsmascotasrazas.models.entity.Mascota;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ms-mascotas")
public interface IMascotasFeingClient {

    @GetMapping("/buscar-mascotas-x-ids")
    List<Mascota> buscarMascotasXIds(@RequestParam List<Long> ids);
}
