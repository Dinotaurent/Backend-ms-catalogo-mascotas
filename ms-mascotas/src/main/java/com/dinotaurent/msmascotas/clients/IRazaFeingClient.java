package com.dinotaurent.msmascotas.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "ms-razas")
public interface IRazaFeingClient {

    @DeleteMapping("/eliminar-razaMascotas/{mascotaId}")
    void  eliminarRazaMascotas(@PathVariable Long mascotaId);
}
