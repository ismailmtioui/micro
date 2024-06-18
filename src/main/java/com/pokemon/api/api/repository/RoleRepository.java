package com.pokemon.api.api.repository;

import com.pokemon.api.api.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

Optional<Role> findByName(String name);
}
