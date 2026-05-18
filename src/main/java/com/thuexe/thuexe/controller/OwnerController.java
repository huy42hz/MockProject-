package com.thuexe.thuexe.controller;

import com.thuexe.thuexe.dto.OwnerRegistrationRequest;
import com.thuexe.thuexe.entity.User;
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

<<<<<<< HEAD
    @GetMapping("/owner-registration")
=======
    @GetMapping("/dang-ky-chu-xe")
>>>>>>> 7cddc4880364de04e392392295efcaf765c67d2c
    public String showOwnerRegistrationForm(Model model, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        
        if (user == null) {
            return "redirect:/auth/login";
        }

        model.addAttribute("ownerRegistrationRequest", new OwnerRegistrationRequest());
        model.addAttribute("user", user);
        return "owner/owner-registration";
    }

<<<<<<< HEAD
    @PostMapping("/owner-registration")
=======
    @PostMapping("/dang-ky-chu-xe")
>>>>>>> 7cddc4880364de04e392392295efcaf765c67d2c
    public String submitOwnerRegistration(@ModelAttribute OwnerRegistrationRequest request,
                                          HttpSession session, Model model) {
        try {
            User user = (User) session.getAttribute("currentUser");
<<<<<<< HEAD
            userService.applyForOwner(user.getUserId(), request);   // ← Sửa thành applyForOwner

            model.addAttribute("success", "Đơn đăng ký trở thành chủ xe đã được gửi thành công!");
=======
            userService.registerAsOwner(user.getUserId(), request);

            model.addAttribute("success", "Đơn đăng ký trở thành chủ xe đã được gửi thành công! Admin sẽ xét duyệt sớm.");
>>>>>>> 7cddc4880364de04e392392295efcaf765c67d2c
            return "owner/owner-registration";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "owner/owner-registration";
        }
    }
}