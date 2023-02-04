package com.dinotaurent.msrazas.models.services;

import com.dinotaurent.mscommons.models.service.CommonsServiceImpl;
import com.dinotaurent.mscommonsmascotasrazas.models.entity.Raza;
import com.dinotaurent.msrazas.models.dao.IRazaDao;
import org.springframework.stereotype.Service;

@Service
public class RazaServiceImpl extends CommonsServiceImpl<Raza, IRazaDao> implements IRazaService {
}
