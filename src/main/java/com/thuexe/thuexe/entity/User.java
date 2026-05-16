package com.thuexe.thuexe.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "NguoiDung")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maNguoiDung")
    private Long maNguoiDung;

    @Column(name = "soDienThoai", nullable = false, unique = true, length = 15)
    private String soDienThoai;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "hoTen", nullable = false, length = 50)
    private String hoTen;

    @Column(name = "matKhau", nullable = false, length = 255)
    private String matKhau;

    @Column(name = "anhDaiDien", length = 200)
    private String anhDaiDien;

    @Column(name = "cccd", length = 20)
    private String cccd;

    @Column(name = "diaChi", length = 200)
    private String diaChi;

    @Column(name = "vaiTro", nullable = false, length = 20)
    private String vaiTro = "KhachHang";

    @Column(name = "trangThai", nullable = false, length = 20)
    private String trangThai = "HoatDong";

    @Column(name = "lyDoKhoa", length = 300)
    private String lyDoKhoa;

    @CreationTimestamp
    @Column(name = "ngayTao", updatable = false)
    private LocalDateTime ngayTao;
}