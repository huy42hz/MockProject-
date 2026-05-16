// entity/DangKyNguoiChoThue.java
package com.thuexe.thuexe.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "DangKyNguoiChoThue")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DangKyNguoiChoThue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maDangKy")
    private Long maDangKy;

    @ManyToOne
    @JoinColumn(name = "maNguoiDung", nullable = false)
    private NguoiDung nguoiDung;

    @Column(name = "soCCCD", nullable = false, length = 20)
    private String soCCCD;

    @Column(name = "anhCCCDMatTruoc", nullable = false, length = 200)
    private String anhCCCDMatTruoc;

    @Column(name = "anhCCCDMatSau", nullable = false, length = 200)
    private String anhCCCDMatSau;

    @Column(name = "soGPLX", nullable = false, length = 20)
    private String soGPLX;

    @Column(name = "anhGPLX", nullable = false, length = 200)
    private String anhGPLX;

    @Column(name = "trangThai", nullable = false, length = 20)
    private String trangThai = "PENDING"; // PENDING, APPROVED, REJECTED

    @Column(name = "lyDoTuChoi", length = 300)
    private String lyDoTuChoi;

    @CreationTimestamp
    @Column(name = "ngayTao")
    private LocalDateTime ngayTao;
}