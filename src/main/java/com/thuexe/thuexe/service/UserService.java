package com.thuexe.thuexe.service;



import com.thuexe.thuexe.dto.RegisterRequest;
import com.thuexe.thuexe.entity.NguoiDung;

public interface UserService {
 NguoiDung register(RegisterRequest request);
 NguoiDung login(String soDienThoai, String matKhau);
 void resetPassword(String email, String newPassword);
}
