package com.thuexe.thuexe.service.impl;

import com.thuexe.thuexe.dto.RegisterRequest;
import com.thuexe.thuexe.entity.NguoiDung;
import com.thuexe.thuexe.repository.NguoiDungRepository;
import com.thuexe.thuexe.service.UserService;
import com.thuexe.thuexe.util.PasswordUtil;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final NguoiDungRepository repository;

    public UserServiceImpl(NguoiDungRepository repository) {
        this.repository = repository;
    }

    @Override
    public NguoiDung register(RegisterRequest req) {

        if (repository.existsBySoDienThoai(req.getSoDienThoai())) {
            throw new RuntimeException("Số điện thoại đã được đăng ký");
        }

        if (repository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }

        if (repository.existsBySoCCCD(req.getSoCCCD())) {
            throw new RuntimeException("CCCD đã tồn tại");
        }

        NguoiDung user = new NguoiDung();

        user.setHoTen(req.getHoTen());
        user.setEmail(req.getEmail());
        user.setSoDienThoai(req.getSoDienThoai());
        user.setDiaChi(req.getDiaChi());
        user.setSoCCCD(req.getSoCCCD());

        user.setMatKhau(
                PasswordUtil.hashPassword(req.getMatKhau())
        );

        user.setVaiTro("CUSTOMER");
        user.setTrangThai("ACTIVE");

        return repository.save(user);
    }
    @Override
    public void resetPassword(String email, String newPassword) {

        NguoiDung user = repository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Email không tồn tại"));

        user.setMatKhau(
                PasswordUtil.hashPassword(newPassword)
        );

        repository.save(user);
    }
    @Override
    public NguoiDung login(String soDienThoai, String matKhau) {

        NguoiDung user = repository.findBySoDienThoai(soDienThoai)
                .orElseThrow(() ->
                        new RuntimeException("Sai số điện thoại hoặc mật khẩu"));

        if ("LOCKED".equals(user.getTrangThai())) {
            throw new RuntimeException("Tài khoản đã bị khóa");
        }

        if (!PasswordUtil.checkPassword(matKhau, user.getMatKhau())) {
            throw new RuntimeException("Sai số điện thoại hoặc mật khẩu");
        }

        return user;
    }
}