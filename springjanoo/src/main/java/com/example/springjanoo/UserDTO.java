package com.example.springjanoo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class UserDTO {
    private String userName;
    private int userAge;
    private String userInfo;
}
