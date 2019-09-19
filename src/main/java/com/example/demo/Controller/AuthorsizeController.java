package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.dto.AccesstokenDTO;
import com.example.demo.dto.GIthubuser;
import com.example.demo.mapper.Usermapper;
import com.example.demo.provider.GitProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorsizeController {
    @Autowired
    private GitProvider gitprovider;


    @Value("${github.client.id}")
    private String clientid;
    @Value("${github.client.secret}")
    private String clientsecret;
    @Value("${github.redirect.uri}")
    private String redirecturi;
    @Autowired
    private Usermapper userMapper;
    @GetMapping("/callback")

    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state,
                           HttpServletResponse response){
        AccesstokenDTO accesstokenDTO=new AccesstokenDTO();
        accesstokenDTO.setClient_id(clientid);
        accesstokenDTO.setClient_secret(clientsecret);
        accesstokenDTO.setCode(code);
        accesstokenDTO.setRedirect_uri(redirecturi);
        accesstokenDTO.setState(state);
        String accesstoken=gitprovider.GetAccessToken(accesstokenDTO);
        GIthubuser githubuser=gitprovider.getUser(accesstoken);
       if(githubuser!=null){
           User user=new User();
           user.setName(githubuser.getName());
           user.setAccount_id(String.valueOf(githubuser.getId()));
           String token=UUID.randomUUID().toString();
           user.setToken(token);
           user.setGmt_create(System.currentTimeMillis());
           user.setGmt_identify(user.getGmt_create());
           userMapper.insert(user);
           response.addCookie(new Cookie("token",token));


           return "redirect:/";
       }else{
           return "redirect:/";
       }


    }
}
