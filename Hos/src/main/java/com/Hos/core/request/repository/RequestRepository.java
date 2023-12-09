package com.Hos.core.request.repository;

import com.Hos.core.common.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>, PagingAndSortingRepository<Request, Long> {

}
