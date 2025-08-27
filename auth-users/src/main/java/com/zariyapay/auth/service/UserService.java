package com.zariyapay.auth.service;

import com.zariyapay.auth.dto.JwtResponse;
import com.zariyapay.auth.dto.LoginRequest;
import com.zariyapay.auth.dto.UserDto;

public interface UserService {

    UserDto findUserById(Long id);;
    UserDto createUser(UserDto userDto);
    String deleteUser(Long id);
    UserDto updateUser(Long id, UserDto userDto);
    String login(LoginRequest loginRequest);
}
