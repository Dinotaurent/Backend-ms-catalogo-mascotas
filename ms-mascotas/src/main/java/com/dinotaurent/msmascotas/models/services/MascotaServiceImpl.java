package com.dinotaurent.msmascotas.models.services;

import com.dinotaurent.mscommons.models.service.CommonsServiceImpl;
import com.dinotaurent.mscommonsmascotasrazas.models.entity.Mascota;
import com.dinotaurent.msmascotas.models.dao.IMascotaDao;
import org.springframework.stereotype.Service;


@Service
public class MascotaServiceImpl extends CommonsServiceImpl<Mascota, IMascotaDao> implements IMascotaService {
}
