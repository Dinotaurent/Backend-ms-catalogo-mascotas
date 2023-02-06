package com.dinotaurent.msrazas.models.services;

import com.dinotaurent.mscommons.models.service.ICommonsService;
import com.dinotaurent.mscommonsmascotasrazas.models.entity.Mascota;
import com.dinotaurent.mscommonsmascotasrazas.models.entity.Raza;

import java.util.List;

public interface IRazaService extends ICommonsService<Raza> {

    void eliminarRazaMascotasXId(Long mascotaId);

    List<Mascota> buscarMascotasXIds(List<Long> ids);

    List<Raza> findByNombreContaining(String termino);
}
