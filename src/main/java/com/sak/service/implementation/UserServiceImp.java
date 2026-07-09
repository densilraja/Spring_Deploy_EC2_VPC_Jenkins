package com.sak.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.sak.entity.User;
import com.sak.repository.UserRepository;
import com.sak.service.UserService;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ModelAndView saveUser(User user) {
        userRepository.save(user);
        return new ModelAndView("redirect:/login");
    }

    @Override
    public User authenticate(String userName, String password) {
        return userRepository.findByUserNameAndPassword(userName, password);
    }
    
}
