package com.lmk.core.web.controller;

import com.lmk.core.po.Customer;
import com.lmk.core.po.User;
import com.lmk.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login.action",method = RequestMethod.POST)
    public String login(String usercode, String password, Model model, HttpSession session){
        User user=userService.findUser(usercode,password);
        if(user!=null){
            session.setAttribute("USER_SESSION",user);
            return "redirect:customer/list.action";
        }
        model.addAttribute("msg","账号或密码错误");
        return "login";
    }
    @RequestMapping("/toCustomer.action")
    public String toCustomer(){
        return "customer";
    }
    @RequestMapping("/logout.action")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:login.action";
    }
    @RequestMapping(value = "/login.action",method = RequestMethod.GET)
    public String toLogin(){
        return "login";
    }
}
