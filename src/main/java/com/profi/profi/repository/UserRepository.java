package com.profi.profi.repository;


import com.profi.profi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    /***  Jpa Methods  ***/

    List<User> findByEmailEndsWith(String email1);

    List<User> findTop2ByNameStartsWith(String name1);

    List<User> findBySurnameContaining(String surname);

    @Query(value = "select * from person1 order by id asc", nativeQuery = true)
    List<User> SortUsersById();

    @Query(value = "select * from person1 order by id desc limit 2", nativeQuery = true)
    List<User> findLastTwoRows(String name2);

    @Query(value = "select * from person1 order by name desc", nativeQuery = true)
    List<User> SortByName(String name3);
    List<User> findByEmailNotContaining(String email2);

    @Query(value = "select * from person1 where name = surname", nativeQuery = true)
    List<User> EqualNameSurname(String name4);

    @Query(value = "select distinct on(name) * from person1", nativeQuery = true)
    List<User> findDistinctByName(String name5);

    @Query(value = "select * from person1 where email like '%narxoz.kz%' or email like '%gmail.com' or email like '%yandex.ru%'", nativeQuery = true)
    List<User> findSomeEmail(String email2);
};