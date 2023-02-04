package com.dinotaurent.msmascotas.models.services;

import com.dinotaurent.mscommons.models.service.CommonsServiceImpl;
import com.dinotaurent.mscommonsmascotasrazas.models.entity.Mascota;
import com.dinotaurent.msmascotas.clients.IRazaFeingClient;
import com.dinotaurent.msmascotas.models.dao.IMascotaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
}
