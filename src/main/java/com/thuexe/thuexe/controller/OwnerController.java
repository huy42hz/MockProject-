// controller/OwnerController.java
package com.thuexe.thuexe.controller;

import com.thuexe.thuexe.dto.DangKyChuXeRequest;
import com.thuexe.thuexe.entity.NguoiDung;
import com.thuexe.thuexe.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/owner")
public class OwnerController {

    private final UserService userService;

    public OwnerController(UserService userService) {
        this.userService = userService;
    }

    // ==================== HIỂN THỊ FORM ====================
    @GetMapping("/dang-ky-chu-xe")
    public String showDangKyChuXe(Model model, HttpSession session) {
        NguoiDung user = (NguoiDung) session.getAttribute("currentUser");
        
        if (user == null) {
            return "redirect:/auth/login";
        }

        model.addAttribute("dangKyRequest", new DangKyChuXeRequest());
        model.addAttribute("user", user);   // Truyền thông tin user vào template
        return "owner/dang-ky-chu-xe";
    }

    // ==================== XỬ LÝ FORM ====================
    @PostMapping("/dang-ky-chu-xe")
    public String submitDangKyChuXe(@ModelAttribute DangKyChuXeRequest request,
                                    HttpSession session, Model model) {
        try {
            NguoiDung user = (NguoiDung) session.getAttribute("currentUser");
            userService.dangKyChuXe(user.getMaNguoiDung(), request);

            model.addAttribute("success", "Đơn đăng ký đã được gửi thành công! Admin sẽ xét duyệt sớm nhất.");
            return "owner/dang-ky-chu-xe";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "owner/dang-ky-chu-xe";
        }
    }
}