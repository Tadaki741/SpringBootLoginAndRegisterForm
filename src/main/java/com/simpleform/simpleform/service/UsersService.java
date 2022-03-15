package com.simpleform.simpleform.service;


import com.simpleform.simpleform.model.UsersModel;
import com.simpleform.simpleform.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {


    private UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersModel registerUser(String login, String password , String email){
        if(login != null && password != null){
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);


            //Store user in the database
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
