package com.example.project01.Services;

import com.example.project01.Entities.User;
import com.example.project01.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getOneUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User createOneUserById(User newUser) {
        return userRepository.save(newUser);
    }

    public User updateOneUserById(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            User foundUser = new User();
            foundUser = user.get();
            foundUser.setUsername(newUser.getUsername());
            userRepository.save(foundUser);
            return foundUser;

        } else{
            return null;
        }
    }

    public void deleteOneUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
