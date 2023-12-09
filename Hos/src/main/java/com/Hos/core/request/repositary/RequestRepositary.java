package com.Hos.core.request.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Hos.core.request.Model.Request;

@Repository
public interface RequestRepositary extends JpaRepository<Request, Long>, PagingAndSortingRepository<Request, Long> {

}
