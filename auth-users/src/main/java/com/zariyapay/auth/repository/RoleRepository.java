package com.zariyapay.auth.repository;

import com.zariyapay.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleById(Long id);

}
