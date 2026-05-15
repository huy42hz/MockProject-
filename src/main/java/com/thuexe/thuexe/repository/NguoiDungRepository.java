package com.thuexe.thuexe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.thuexe.thuexe.entity.NguoiDung;

import java.util.Optional;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {

    Optional<NguoiDung> findBySoDienThoai(String soDienThoai);

    boolean existsBySoDienThoai(String soDienThoai);

    boolean existsByEmail(String email);

    boolean existsBySoCCCD(String soCCCD);
    Optional<NguoiDung> findByEmail(String email);
}