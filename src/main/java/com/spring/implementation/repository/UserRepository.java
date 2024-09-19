package com.spring.implementation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.implementation.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

}
