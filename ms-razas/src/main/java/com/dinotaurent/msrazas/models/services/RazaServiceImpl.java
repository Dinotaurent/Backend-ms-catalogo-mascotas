package com.dinotaurent.msrazas.models.services;

import com.dinotaurent.mscommons.models.service.CommonsServiceImpl;
import com.dinotaurent.mscommonsmascotasrazas.models.entity.Mascota;
import com.dinotaurent.mscommonsmascotasrazas.models.entity.Raza;
import com.dinotaurent.msrazas.clients.IMascotasFeingClient;
import com.dinotaurent.msrazas.models.dao.IRazaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RazaServiceImpl extends CommonsServiceImpl<Raza, IRazaDao> implements IRazaService {

    @Autowired
    IMascotasFeingClient mascotaClient;

    @Override
    @Transactional
    public void eliminarRazaMascotasXId(Long mascotaId) {
        dao.eliminarRazaMascotasXId(mascotaId);
    }

    @Override
    public List<Mascota> buscarMascotasXIds(List<Long> ids) {
        return mascotaClient.buscarMascotasXIds(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Raza> findByNombreContaining(String termino) {
        return dao.findByNombreContaining(termino);
    }
}
