package com.thuexe.thuexe.repository;

import com.thuexe.thuexe.entity.OwnerRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OwnerRegistrationRepository extends JpaRepository<OwnerRegistration, Long> {

    // Kiểm tra người dùng đã có đơn đang chờ duyệt chưa
    boolean existsByUser_MaNguoiDungAndTrangThai(Long maNguoiDung, String trangThai);

    // Lấy danh sách đơn đăng ký của một user
    List<OwnerRegistration> findByUser_MaNguoiDung(Long maNguoiDung);
}