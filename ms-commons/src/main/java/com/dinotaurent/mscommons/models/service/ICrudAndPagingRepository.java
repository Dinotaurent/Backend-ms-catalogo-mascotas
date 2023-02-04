package com.dinotaurent.mscommons.models.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICrudAndPagingRepository<T,ID> extends CrudRepository<T,ID>, PagingAndSortingRepository<T,ID> {
}
