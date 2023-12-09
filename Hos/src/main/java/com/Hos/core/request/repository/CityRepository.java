package com.Hos.core.request.repository;

import com.Hos.core.common.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long>, PagingAndSortingRepository<City, Long> {
    List<City> findByIsDeletedFalse();
}
