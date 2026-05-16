// repository/DangKyNguoiChoThueRepository.java
package com.thuexe.thuexe.repository;

import com.thuexe.thuexe.entity.DangKyNguoiChoThue;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DangKyNguoiChoThueRepository extends JpaRepository<DangKyNguoiChoThue, Long> {

    boolean existsByNguoiDung_MaNguoiDungAndTrangThai(Long maNguoiDung, String trangThai);

    List<DangKyNguoiChoThue> findByNguoiDung_MaNguoiDung(Long maNguoiDung);
}