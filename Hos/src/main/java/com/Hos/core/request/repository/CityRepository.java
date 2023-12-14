package com.Hos.core.request.repository;

import com.Hos.core.common.model.City;
import com.Hos.core.common.util.Constants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long>, PagingAndSortingRepository<City, Long> {

    public static final String GET_CITY_BY_ID = "select city from City as city where"
            + " city.id =:id and city.isActive = true and city.isDeleted = false";

//    public static final String GET_CITY_BY_NAME = "select city from City as city where " +
//            " (:name is NULL or city.name =:name)";

    public static final String GET_CITY_BY_NAME = "select city from City as city where city.name ILIKE CONCAT(:name, '%') " +
            " and city.isActive = true and city.isDeleted = false";

    List<City> findByIsDeletedFalse();

    @Query(value = GET_CITY_BY_ID)
    City getCityById(@Param(Constants.ID) long id);

    @Query(value = GET_CITY_BY_NAME)
    City getCityByName(@Param(Constants.NAME) String name);
}
