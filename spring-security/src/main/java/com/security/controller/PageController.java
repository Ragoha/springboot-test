package com.security.controller;

import com.security.entity.RoleType;
import com.security.entity.User;
import com.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /* ROLE_USER 권한 페이지*/
    @GetMapping("/user")
    public @ResponseBody String user() {
        return "user";
    }

    /* ROLE_MANAGER 권한 페이지*/
    @GetMapping("/manager")
    public @ResponseBody String manager() {
        return "manager";
    }

    /* ROLE_ADMIN 권한 페이지*/
    @GetMapping("/admin")
    public @ResponseBody String admin() {
        return "admin";
    }


    /* 회원가입 Form 이동 */
    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("roleTypes",RoleType.values());

        return "joinForm";
    }

    /* 회원가입 Process */
    @PostMapping("/joinProc")
    public String join(User user) {
        System.out.println(user);

//        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);

        userRepository.save(user);

        return "redirect:/login";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    public @ResponseBody String info(){

        return "개인정보";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/data")
    public @ResponseBody String data(){

        return "데이터정보";
    }
}
