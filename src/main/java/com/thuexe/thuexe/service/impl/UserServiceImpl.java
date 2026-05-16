package com.thuexe.thuexe.service.impl;

import com.thuexe.thuexe.dto.DangKyChuXeRequest;
import com.thuexe.thuexe.dto.RegisterRequest;
import com.thuexe.thuexe.entity.DangKyNguoiChoThue;
import com.thuexe.thuexe.entity.NguoiDung;
import com.thuexe.thuexe.repository.DangKyNguoiChoThueRepository;  // ← Thêm import này
import com.thuexe.thuexe.repository.NguoiDungRepository;
import com.thuexe.thuexe.service.UserService;
import com.thuexe.thuexe.util.PasswordUtil;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final NguoiDungRepository repository;
    private final DangKyNguoiChoThueRepository dangKyRepo;   // ← Thêm dòng này

    // Constructor cập nhật
    public UserServiceImpl(NguoiDungRepository repository, 
                           DangKyNguoiChoThueRepository dangKyRepo) {
        this.repository = repository;
        this.dangKyRepo = dangKyRepo;
    }

    // === Các method khác giữ nguyên (register, login, resetPassword) ===

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
        user.setMatKhau(PasswordUtil.hashPassword(req.getMatKhau()));
        user.setVaiTro("CUSTOMER");
        user.setTrangThai("ACTIVE");

        return repository.save(user);
    }

    @Override
    public void resetPassword(String email, String newPassword) {
        NguoiDung user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email không tồn tại"));

        user.setMatKhau(PasswordUtil.hashPassword(newPassword));
        repository.save(user);
    }

    @Override
    public NguoiDung login(String soDienThoai, String matKhau) {
        NguoiDung user = repository.findBySoDienThoai(soDienThoai)
                .orElseThrow(() -> new RuntimeException("Sai số điện thoại hoặc mật khẩu"));

        if ("LOCKED".equals(user.getTrangThai())) {
            throw new RuntimeException("Tài khoản đã bị khóa");
        }

        if (!PasswordUtil.checkPassword(matKhau, user.getMatKhau())) {
            throw new RuntimeException("Sai số điện thoại hoặc mật khẩu");
        }

        return user;
    }

    @Override
    public void dangKyChuXe(Long maNguoiDung, DangKyChuXeRequest req) {
        NguoiDung user = repository.findById(maNguoiDung)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        // Kiểm tra đã có đơn đang chờ duyệt chưa
        if (dangKyRepo.existsByNguoiDung_MaNguoiDungAndTrangThai(maNguoiDung, "PENDING")) {
            throw new RuntimeException("Bạn đã có đơn đăng ký đang chờ duyệt!");
        }

        DangKyNguoiChoThue dk = new DangKyNguoiChoThue();
        dk.setNguoiDung(user);
        dk.setSoCCCD(req.getSoCCCD());
        dk.setSoGPLX(req.getSoGPLX());

        // Lưu tên file tạm thời (sau này sẽ cải tiến upload file)
        dk.setAnhCCCDMatTruoc(req.getAnhCCCDMatTruoc() != null ? 
                req.getAnhCCCDMatTruoc().getOriginalFilename() : null);
        dk.setAnhCCCDMatSau(req.getAnhCCCDMatSau() != null ? 
                req.getAnhCCCDMatSau().getOriginalFilename() : null);
        dk.setAnhGPLX(req.getAnhGPLX() != null ? 
                req.getAnhGPLX().getOriginalFilename() : null);

        dk.setTrangThai("PENDING");

        dangKyRepo.save(dk);
    }
}