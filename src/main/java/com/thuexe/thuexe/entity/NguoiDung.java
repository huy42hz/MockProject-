package com.thuexe.thuexe.entity;



import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "NguoiDung")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class NguoiDung {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "maNguoiDung")
 private Long maNguoiDung;

 @Column(name = "hoTen", nullable = false, length = 50)
 private String hoTen;

 @Column(name = "email", nullable = false, unique = true, length = 100)
 private String email;

 @Column(name = "soDienThoai", nullable = false, unique = true, length = 15)
 private String soDienThoai;

 @Column(name = "matKhau", nullable = false)
 private String matKhau;

 @Column(name = "vaiTro", nullable = false, length = 20)
 private String vaiTro = "CUSTOMER";

 @Column(name = "trangThai", nullable = false, length = 20)
 private String trangThai = "ACTIVE";

 @Column(name = "anhDaiDien", length = 200)
 private String anhDaiDien;

 @Column(name = "diaChi", length = 200)
 private String diaChi;

 @Column(name = "soCCCD", length = 20)
 private String soCCCD;

 @CreationTimestamp
 @Column(name = "ngayTao")
 private LocalDateTime ngayTao;
}
