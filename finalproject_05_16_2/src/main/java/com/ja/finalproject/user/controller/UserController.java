package com.ja.finalproject.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.finalproject.dto.UserDto;
import com.ja.finalproject.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("loginPage")
    public String loginPage() {

        return "user/loginPage";
    }

    @RequestMapping("registerPage")
    public String registerPage() {

        return "user/registerPage";
    }

    @RequestMapping("registerProcess")
    public String registerProcess(UserDto params) {

        // System.out.println("registerProcess");
        // System.out.println(params);

        userService.register(params);

        return "redirect:./loginPage";
    }

    @RequestMapping("loginProcess")
    public String loginProcess(HttpSession session, UserDto params) {

        UserDto sessionUserInfo = userService.getUserByAccountNameAndPassword(params);

        if(sessionUserInfo == null){
            return "user/loginFail";
        }else{
            // session에 인증 정보를 담는다. 핵심 id가 꼭 담겨야함
            session.setAttribute("sessionUserInfo", sessionUserInfo);
            // return "redirect:../board/mainPage";
            return "redirect:/board/mainPage";
        }
    }

    @RequestMapping("logoutProcess")
    public String logoutProcess(HttpSession session) {
        session.invalidate();

        return "redirect:../board/mainPage";
    }


}
