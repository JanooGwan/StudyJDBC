package com.example.springjanoo;

import com.example.springjanoo.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    static public ArrayList<UserDTO> users;

    static {
        users= new ArrayList<UserDTO>();
        users.add(new UserDTO("kwanwoo", 23, "hello"));
    }
    public UserDTO insertUser(UserDTO user) {
        users.add(user);
        return user;
    }

    public List<UserDTO> getAllUsers() {
        return users;
    }

    public UserDTO getUserByUserName(String userName) {
        return users.stream()
                .filter(userDTO -> userDTO.getUserName().equals(userName))
                .findAny()
                .orElse(new UserDTO("", 0, ""));
    }

    public void updateUserName(String userName, UserDTO user) {
        users.stream()
                .filter(userDTO -> userDTO.getUserName().equals(userName))
                .findAny()
                .orElse(new UserDTO("", 0, ""))
                .setUserName(user.getUserName());
    }

    public void updateUserInfo(String userName, UserDTO user) {
        users.stream()
                .filter(userDTO -> userDTO.getUserName().equals(userName))
                .findAny()
                .orElse(new UserDTO("", 0, ""))
                .setUserInfo(user.getUserInfo());
    }

    public void deleteUser(String userName) {
        users.removeIf(userDTO -> userDTO.getUserName().equals(userName));
    }
}
