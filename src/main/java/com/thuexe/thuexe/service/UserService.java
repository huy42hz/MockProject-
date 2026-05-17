package com.thuexe.thuexe.service;

import com.thuexe.thuexe.dto.OwnerRegistrationRequest;
import com.thuexe.thuexe.dto.RegisterRequest;
import com.thuexe.thuexe.entity.User;

public interface UserService {
    User register(RegisterRequest request);
    User login(String phone, String password);
    void resetPassword(String email, String newPassword);
    void registerAsOwner(Long userId, OwnerRegistrationRequest request);
}