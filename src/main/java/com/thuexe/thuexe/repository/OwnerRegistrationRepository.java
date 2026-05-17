package com.thuexe.thuexe.repository;

import com.thuexe.thuexe.entity.OwnerRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OwnerRegistrationRepository extends JpaRepository<OwnerRegistration, Long> {

    boolean existsByUser_UserIdAndStatus(Long userId, String status);

    List<OwnerRegistration> findByUser_UserId(Long userId);
}