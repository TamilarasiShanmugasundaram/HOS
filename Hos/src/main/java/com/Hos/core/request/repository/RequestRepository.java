package com.Hos.core.request.repository;

import java.util.List;

import com.Hos.core.common.model.Request;
import com.Hos.core.common.util.Constants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>, PagingAndSortingRepository<Request, Long> {

    public static final String GET_REQUESTS_INCLUDING_CREATED_BY = "select distinct req from Request req "
    + "join req.cities as rc where (:type IS NULL OR req.type = :type) "
    + "AND (:cityIds IS NULL OR rc.id IN :cityIds) "
    + "AND (:created_by IS NULL OR req.createdBy = :created_by) AND req.isRequestClosed = false "
    + "order by req.createdBy desc, req.createdAt desc";
public static final String GET_REQUESTS_EXCLUDING_CREATED_BY = "select distinct req from Request req "
    + "join req.cities as rc where (:type IS NULL OR req.type = :type) "
    + "AND (:cityIds IS NULL OR rc.id IN :cityIds) "
    + "AND (:created_by IS NULL OR req.createdBy != :created_by) AND req.isRequestClosed = false "
    + "order by req.createdBy desc, req.createdAt desc";



    Request findByIdAndIsDeletedFalseAndIsRequestClosedFalse(long id);

    @Query(value = GET_REQUESTS_INCLUDING_CREATED_BY)
    List<Request> getRequestsIncludingCreatedBy(@Param(Constants.TYPE) String type,
                                                @Param(Constants.CITY_IDS) List<Long> cityId,
                                                @Param(Constants.CREATED_BY) Long createdBy);

	@Query(value = GET_REQUESTS_EXCLUDING_CREATED_BY)
	List<Request> getRequestsExcludingCreatedBy(@Param(Constants.TYPE) String type,
	                                            @Param(Constants.CITY_IDS) List<Long> cityId,
	                                            @Param(Constants.CREATED_BY) Long createdBy);

	Request findByIdAndIsDeletedFalse(Long requestId);

    List<Request> findByIsRequestClosedFalseOrderByIdDesc();

}
