package com.kristaps.back_end.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kristaps.back_end.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    boolean existsByEmail(String email);

    UserModel findByEmailAndPassword(String email, String password);

}
