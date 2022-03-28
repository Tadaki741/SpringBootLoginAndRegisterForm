package com.simpleform.simpleform.repository;

import com.simpleform.simpleform.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersModel,Integer> {


    //Find by login and password
    Optional<UsersModel> findByLoginAndPassword(String login, String password);

    //Check if the user register with the existed username
    public boolean existsByEmail(String email);
}
