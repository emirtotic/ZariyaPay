package com.zariyapay.auth.mapper;

import com.zariyapay.auth.dto.UserDto;
import com.zariyapay.auth.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { RoleMapper.class })
public interface UserMapper {

    @Mapping(target = "roleId", source = "role.id")
    UserDto mapToDto(User user);
    User mapToEntity(UserDto dto);
    List<UserDto> mapToDto(List<User> user);
    List<User> mapToEntity(List<UserDto> dtos);

}
