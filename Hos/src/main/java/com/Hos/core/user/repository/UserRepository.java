package com.Hos.core.user.repository;

import com.Hos.core.common.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long> {
    List<User> findByIsDeletedFalse();

    User findByUsernameAndIsDeletedFalse(String username);
    User findByIdAndIsDeletedFalse(long id);
    User findByUsernameAndPasswordAndIsDeletedFalse(String username, String password);

    List<User> findByCityIdAndIsDeletedFalse(long cityId);
    List<User> findByCityIdAndIsCommunityUserTrueAndIsDeletedFalse(long cityId);
}
