package com.rafsanjani.sandstorm.service;

import com.rafsanjani.sandstorm.model.User;
import com.rafsanjani.sandstorm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user){

        return userRepository.save(user);
    }
}
