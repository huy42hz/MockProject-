// dto/DangKyChuXeRequest.java
package com.thuexe.thuexe.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DangKyChuXeRequest {

    @NotBlank(message = "Số CCCD không được để trống")
    private String soCCCD;

    private MultipartFile anhCCCDMatTruoc;
    private MultipartFile anhCCCDMatSau;

    @NotBlank(message = "Số GPLX không được để trống")
    private String soGPLX;

    private MultipartFile anhGPLX;
}