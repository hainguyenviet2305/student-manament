package org.vti.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vti.studentmanagement.entity.Role;
import org.vti.studentmanagement.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(Role role);
    Optional<User> findByUsername(String username);
}
