package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.mapper.Usermapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletRequest;


@Controller
public class indexController {
    @Autowired
    private Usermapper usermapper;
    @GetMapping("/")
    public  String index(HttpServletRequest request){
        Cookie[] cookie=request.getCookies();
        for(Cookie c1:cookie){
            if(c1.getName().equals("token")){
                String token=c1.getValue();
                User user=usermapper.findbytoken(token);
                if(user!=null){
                    request.getSession().setAttribute("user",user);
                }
            }
        }


        return "index";

    }

}
