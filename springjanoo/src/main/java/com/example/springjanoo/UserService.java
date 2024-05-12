package com.example.springjanoo;

import com.example.springjanoo.UserRepository;
import com.example.springjanoo.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserDTO insertUser(UserDTO user) {
        return userRepository.insertUser(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public UserDTO getUserByUserName(String userName) {
        return userRepository.getUserByUserName(userName);
    }

    public void updateUserName(String userName, UserDTO user) {
        userRepository.updateUserName(userName, user);
    }

    public void updateUserInfo(String userName, UserDTO user) {
        userRepository.updateUserInfo(userName, user);
    }

    public void deleteUser(String userName) {
        userRepository.deleteUser(userName);
    }
}
