package com.example.demo.Controller;

import com.example.demo.dto.AccesstokenDTO;
import com.example.demo.dto.GIthubuser;
import com.example.demo.provider.GitProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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
    @GetMapping("/callback")

    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state,
                           HttpServletRequest request){
        AccesstokenDTO accesstokenDTO=new AccesstokenDTO();
        accesstokenDTO.setClient_id(clientid);
        accesstokenDTO.setClient_secret(clientsecret);
        accesstokenDTO.setCode(code);
        accesstokenDTO.setRedirect_uri(redirecturi);
        accesstokenDTO.setState(state);
        String accesstoken=gitprovider.GetAccessToken(accesstokenDTO);
        GIthubuser user=gitprovider.getUser(accesstoken);
       if(user!=null){
           request.getSession().setAttribute("user",user);
           return "redirect:/";
       }else{
           return "redirect:/";
       }


    }
}
