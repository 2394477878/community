package com.example.demo.provider;

import com.alibaba.fastjson.JSON;
import com.example.demo.dto.AccesstokenDTO;
import com.example.demo.dto.GIthubuser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitProvider {
    public String GetAccessToken(AccesstokenDTO accesstokendto){
        final MediaType mdiaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create( mdiaType, JSON.toJSONString(accesstokendto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string=response.body().string();
            String token=string.split( "&")[0].split("=")[1];
            System.out.println(token);

                return token;
            } catch (Exception e) {
                e.printStackTrace();
            }

        return null;
    }
    public GIthubuser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://api.github.com/user?access_token="+accessToken)
                    .build();

            try  {
                Response response = client.newCall(request).execute();
               String string=response.body().string();
                GIthubuser githubuser=JSON.parseObject(string,GIthubuser.class);
                return githubuser;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

    }
}
