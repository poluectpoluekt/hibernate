package com.edu.hibernate.controller;


import com.edu.hibernate.dto.UserDto;
import com.edu.hibernate.dto.UserDtoResponse;
import com.edu.hibernate.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    public UserDtoResponse register(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }
}
