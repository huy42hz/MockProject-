package com.thuexe.thuexe.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "DangKyChuXe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maDangKy")
    private Long maDangKy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNguoiDung", nullable = false)
    private User user;

    @Column(name = "soCCCD", nullable = false, length = 20)
    private String soCCCD;

    @Column(name = "anhCCCDMatTruoc", nullable = false, length = 200)
    private String anhCCCDMatTruoc;

    @Column(name = "anhCCCDMatSau", nullable = false, length = 200)
    private String anhCCCDMatSau;

    @Column(name = "trangThai", nullable = false, length = 20)
    private String trangThai = "ChoDuyet";

    @Column(name = "lyDoTuChoi", length = 300)
    private String lyDoTuChoi;

    @CreationTimestamp
    @Column(name = "ngayGui", updatable = false)
    private LocalDateTime ngayGui;
}