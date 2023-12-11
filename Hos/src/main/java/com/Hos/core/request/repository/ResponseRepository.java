package com.Hos.core.request.repository;

import com.Hos.core.common.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long>, PagingAndSortingRepository<Response, Long> {
}
