package com.simpleform.simpleform.service;


import com.simpleform.simpleform.model.UsersModel;
import com.simpleform.simpleform.repository.UsersRepository;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.SessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {


    private UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersModel registerUser(String login, String password , String email){
        //If the user type in all the field
        if(login != null && password != null){
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);

            //We check if the provided email already existed in the database
            if(usersRepository.existsByEmail(email)){
                System.out.println("Email already taken: " + email);
                return null;
            }

            return usersRepository.save(usersModel);
        }

        else{
            return null;
        }
    }

    public UsersModel authenticate(String login , String password){
        return usersRepository.findByLoginAndPassword(login,password).orElse(null);
    }



}
