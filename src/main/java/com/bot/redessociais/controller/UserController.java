package com.bot.redessociais.controller;

import com.bot.redessociais.model.User;
import com.bot.redessociais.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    public static UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void insertUserSuccesfuly(User userNew) {
        userRepository.save(userNew);
    }

}
