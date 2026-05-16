package com.thuexe.thuexe.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class OwnerRegistrationRequest {

    @NotBlank(message = "Số CCCD không được để trống")
    private String soCCCD;

    private MultipartFile anhCCCDMatTruoc;
    private MultipartFile anhCCCDMatSau;

    // Nếu sau này thêm GPLX thì bổ sung ở đây
    // private String soGPLX;
    // private MultipartFile anhGPLX;
}