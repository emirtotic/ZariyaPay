package com.zariyapay.auth.service.impl;

import com.zariyapay.auth.dto.UserDto;
import com.zariyapay.auth.entity.Role;
import com.zariyapay.auth.entity.User;
import com.zariyapay.auth.mapper.UserMapper;
import com.zariyapay.auth.repository.RoleRepository;
import com.zariyapay.auth.repository.UserRepository;
import com.zariyapay.auth.service.UserService;
import com.zariyapay.common.error.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto findUserById(Long id) {

        User user = Optional.ofNullable(userRepository.findUserById(id))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        return userMapper.mapToDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = userMapper.mapToEntity(userDto);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        Role role = roleRepository.findRoleById(userDto.getRoleId());
        user.setRole(role);
        userRepository.save(user);
        return userMapper.mapToDto(user);
    }

    @Override
    @Transactional
    public String deleteUser(Long id) {

        User user = Optional.ofNullable(userRepository.findUserById(id))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        userRepository.deleteUserById(user.getId());

        return "User has been deleted successfully.";
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {

        User user = Optional.ofNullable(userRepository.findUserById(id))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        user.setPasswordHash(userDto.getPasswordHash());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setActive(userDto.isActive());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setUpdatedAt(userDto.getUpdatedAt());
        user.setUpdatedAt(LocalDateTime.now());

        Role role = roleRepository.findRoleById(userDto.getRoleId());
        user.setRole(role);

        userRepository.save(user);

        return userMapper.mapToDto(user);
    }
}
