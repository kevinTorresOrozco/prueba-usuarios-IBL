package com.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.users.dominio.Users;

@Repository
public interface IUsersRep extends JpaRepository<Users, Integer>{

}
