package com.thuexe.thuexe.repository;

import com.thuexe.thuexe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findBySoDienThoai(String soDienThoai);
    
    boolean existsBySoDienThoai(String soDienThoai);
    boolean existsByEmail(String email);
    boolean existsByCccd(String cccd);           // ← Quan trọng: theo cột mới
    
    Optional<User> findByEmail(String email);
}