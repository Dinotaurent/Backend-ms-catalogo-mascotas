package com.dinotaurent.msmascotas.models.dao;

import com.dinotaurent.mscommons.models.service.ICrudAndPagingRepository;
import com.dinotaurent.mscommonsmascotasrazas.models.entity.Mascota;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMascotaDao extends ICrudAndPagingRepository<Mascota, Long> {

    @Query("select m from Mascota m where upper(m.nombre) like upper(concat('%',?1,'%'))")
    List<Mascota> findByNombreContaining(String termino);
    List<Mascota> findAllByOrderByIdAsc();
    Page<Mascota> findAllByOrderByIdAsc(Pageable pageable);

}
