package com.zariyapay.auth.mapper;

import com.zariyapay.auth.dto.RoleDto;
import com.zariyapay.auth.dto.UserDto;
import com.zariyapay.auth.entity.Role;
import com.zariyapay.auth.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto toDto(Role role);
    List<RoleDto> toDtoList(List<Role> roles);
    @Mapping(target = "users", ignore = true)
    Role toEntity(RoleDto roleDto);
}
