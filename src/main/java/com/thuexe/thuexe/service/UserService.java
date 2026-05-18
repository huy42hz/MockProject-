package com.thuexe.thuexe.service;

import com.thuexe.thuexe.dto.OwnerRegistrationRequest;
import com.thuexe.thuexe.dto.RegisterRequest;
import com.thuexe.thuexe.entity.User;

public interface UserService {
    User register(RegisterRequest request);
    User login(String phone, String password);
    void resetPassword(String email, String newPassword);
<<<<<<< HEAD
    void applyForOwner(Long userId, OwnerRegistrationRequest request);
=======
    void registerAsOwner(Long userId, OwnerRegistrationRequest request);
>>>>>>> 7cddc4880364de04e392392295efcaf765c67d2c
}