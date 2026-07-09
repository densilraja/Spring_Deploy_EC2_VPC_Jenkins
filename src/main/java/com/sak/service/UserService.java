package com.sak.service;

import org.springframework.web.servlet.ModelAndView;

import com.sak.entity.User;


public interface UserService {
    ModelAndView saveUser(User user);
    User authenticate(String userName, String password);
}
