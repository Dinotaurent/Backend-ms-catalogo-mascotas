package com.dinotaurent.msmascotas.models.services;

import com.dinotaurent.mscommons.models.service.CommonsServiceImpl;
import com.dinotaurent.mscommonsmascotasrazas.models.entity.Mascota;
import com.dinotaurent.msmascotas.clients.IRazaFeingClient;
import com.dinotaurent.msmascotas.models.dao.IMascotaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class MascotaServiceImpl extends CommonsServiceImpl<Mascota, IMascotaDao> implements IMascotaService {

    @Autowired
    IRazaFeingClient razaClient;

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
        razaClient.eliminarRazaMascotas(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mascota> findAllByIds(List<Long> ids) {
        return (List<Mascota>) dao.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mascota> findByNombreContaining(String termino) {
        return dao.findByNombreContaining(termino);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mascota> findAll() {
        return dao.findAllByOrderByIdAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Mascota> findAll(Pageable pageable) {
        return dao.findAllByOrderByIdAsc(pageable);
    }
}
