package com.thuexe.thuexe.dto;

//dto/LoginRequest.java


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
 @NotBlank(message = "Số điện thoại không được để trống")
 private String soDienThoai;

 @NotBlank(message = "Mật khẩu không được để trống")
 private String matKhau;
}