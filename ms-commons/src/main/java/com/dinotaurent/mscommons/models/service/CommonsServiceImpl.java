package com.dinotaurent.mscommons.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class CommonsServiceImpl<E, R extends ICrudAndPagingRepository<E,Long>> implements ICommonsService<E>{

    @Autowired
    protected R dao;

    @Override
    public List<E> findAll() {
        return (List<E>) dao.findAll();
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public Optional<E> findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public E save(E entity) {
        return dao.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
