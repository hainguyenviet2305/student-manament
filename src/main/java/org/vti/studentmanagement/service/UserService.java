package org.vti.studentmanagement.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.vti.studentmanagement.entity.Role;
import org.vti.studentmanagement.entity.User;
import org.vti.studentmanagement.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User create(User user) {
        if (user.getRole() == null) {
            user.setRole(Role.USER); // Đặt vai trò mặc định là USER
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Mã hóa mật khẩu
        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setName(user.getName());
            updatedUser.setUsername(user.getUsername());
            updatedUser.setPassword(passwordEncoder.encode(user.getPassword())); // Mã hóa mật khẩu
            updatedUser.setRole(user.getRole());
            return userRepository.save(updatedUser);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        User user = optionalUser.get(); // Lấy đối tượng User từ Optional
        Role role = user.getRole(); // Lấy role từ User
        GrantedAuthority authority = new SimpleGrantedAuthority(role.name()); // Sử dụng name() của enum Role để lấy tên
        System.out.println("User: " + user.getUsername() + ", Role: " + role.name());
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(authority) // Sử dụng Collections.singletonList để tạo một List chỉ chứa một phần tử
        );
    }
}
