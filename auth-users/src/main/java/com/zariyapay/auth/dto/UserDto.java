package com.zariyapay.auth.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long id;
    private Long externalId;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private LocalDate createdAt;
    private LocalDate updatedAt;
//    private RoleDto role;
    private long roleId;


}
