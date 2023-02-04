package com.dinotaurent.msrazas.models.services;

import com.dinotaurent.mscommons.models.service.CommonsServiceImpl;
import com.dinotaurent.mscommonsmascotasrazas.models.entity.Raza;
import com.dinotaurent.msrazas.models.dao.IRazaDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RazaServiceImpl extends CommonsServiceImpl<Raza, IRazaDao> implements IRazaService {
    @Override
    @Transactional
    public void eliminarRazaMascotasXId(Long mascotaId) {
        dao.eliminarRazaMascotasXId(mascotaId);
    }
}
