package com.jsonmapper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsonmapper.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUserName(String userName);
}
