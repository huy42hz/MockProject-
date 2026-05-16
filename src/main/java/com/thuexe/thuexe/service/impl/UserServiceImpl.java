package com.thuexe.thuexe.service.impl;

import com.thuexe.thuexe.dto.OwnerRegistrationRequest;
import com.thuexe.thuexe.dto.RegisterRequest;
import com.thuexe.thuexe.entity.OwnerRegistration;
import com.thuexe.thuexe.entity.User;
import com.thuexe.thuexe.repository.OwnerRegistrationRepository;
import com.thuexe.thuexe.repository.UserRepository;
import com.thuexe.thuexe.service.UserService;
import com.thuexe.thuexe.util.PasswordUtil;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OwnerRegistrationRepository ownerRegistrationRepository;

    public UserServiceImpl(UserRepository userRepository, 
                           OwnerRegistrationRepository ownerRegistrationRepository) {
        this.userRepository = userRepository;
        this.ownerRegistrationRepository = ownerRegistrationRepository;
    }

    @Override
    public User register(RegisterRequest req) {
        if (userRepository.existsBySoDienThoai(req.getSoDienThoai())) {
            throw new RuntimeException("Số điện thoại đã được đăng ký");
        }
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }
        if (userRepository.existsByCccd(req.getSoCCCD())) {  // cccd
            throw new RuntimeException("CCCD đã tồn tại");
        }

        User user = new User();
        user.setHoTen(req.getHoTen());
        user.setEmail(req.getEmail());
        user.setSoDienThoai(req.getSoDienThoai());
        user.setDiaChi(req.getDiaChi());
        user.setCccd(req.getSoCCCD());           // ← quan trọng
        user.setMatKhau(PasswordUtil.hashPassword(req.getMatKhau()));
        user.setVaiTro("CUSTOMER");
        user.setTrangThai("ACTIVE");

        return userRepository.save(user);
    }

    @Override
    public User login(String soDienThoai, String matKhau) {
        User user = userRepository.findBySoDienThoai(soDienThoai)
                .orElseThrow(() -> new RuntimeException("Sai số điện thoại hoặc mật khẩu"));

        if ("BiKhoa".equals(user.getTrangThai())) {
            throw new RuntimeException("Tài khoản đã bị khóa");
        }

        if (!PasswordUtil.checkPassword(matKhau, user.getMatKhau())) {
            throw new RuntimeException("Sai số điện thoại hoặc mật khẩu");
        }
        return user;
    }

    @Override
    public void registerAsOwner(Long maNguoiDung, OwnerRegistrationRequest req) {
        User user = userRepository.findById(maNguoiDung)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        if (ownerRegistrationRepository.existsByUser_MaNguoiDungAndTrangThai(maNguoiDung, "ChoDuyet")) {
            throw new RuntimeException("Bạn đã có đơn đăng ký đang chờ duyệt!");
        }

        OwnerRegistration reg = new OwnerRegistration();
        reg.setUser(user);
        reg.setSoCCCD(req.getSoCCCD());
        reg.setAnhCCCDMatTruoc(req.getAnhCCCDMatTruoc() != null ? 
                              req.getAnhCCCDMatTruoc().getOriginalFilename() : null);
        reg.setAnhCCCDMatSau(req.getAnhCCCDMatSau() != null ? 
                            req.getAnhCCCDMatSau().getOriginalFilename() : null);
        reg.setTrangThai("ChoDuyet");

        ownerRegistrationRepository.save(reg);
    }

    @Override
    public void resetPassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email không tồn tại"));
        user.setMatKhau(PasswordUtil.hashPassword(newPassword));
        userRepository.save(user);
    }
}
