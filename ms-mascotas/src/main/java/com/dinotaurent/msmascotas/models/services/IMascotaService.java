package com.dinotaurent.msmascotas.models.services;

import com.dinotaurent.mscommons.models.service.ICommonsService;
import com.dinotaurent.mscommonsmascotasrazas.models.entity.Mascota;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMascotaService extends ICommonsService<Mascota> {

    List<Mascota> findAllByIds(List<Long> ids);

    List<Mascota> findByNombreContaining(String termino);
}
