package com.thuexe.thuexe.service;

import com.thuexe.thuexe.dto.OwnerRegistrationRequest;
import com.thuexe.thuexe.dto.RegisterRequest;
import com.thuexe.thuexe.entity.User;

public interface UserService {
    User register(RegisterRequest request);
    User login(String soDienThoai, String matKhau);
    void resetPassword(String email, String newPassword);
    void registerAsOwner(Long maNguoiDung, OwnerRegistrationRequest request);
}