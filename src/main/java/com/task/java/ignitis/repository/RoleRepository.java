package com.task.java.ignitis.repository;

import com.task.java.ignitis.entity.Role;
import com.task.java.ignitis.enums.ERole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
