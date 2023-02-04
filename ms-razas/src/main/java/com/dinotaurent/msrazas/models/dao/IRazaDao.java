package com.dinotaurent.msrazas.models.dao;

import com.dinotaurent.mscommons.models.service.ICrudAndPagingRepository;
import com.dinotaurent.mscommonsmascotasrazas.models.entity.Raza;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IRazaDao extends ICrudAndPagingRepository<Raza,Long> {

    @Modifying
    @Query("delete from RazaMascotas rm where rm.mascotaId = ?1")
    void eliminarRazaMascotasXId(Long mascotaId);
}
