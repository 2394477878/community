package com.example.demo.Model;

public class User {
    private Integer id;
    private String name;
    private String account_id;
    private String token;
    private long gmt_create;
    private long gmt_identify;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getGmt_identify() {
        return gmt_identify;
    }

    public void setGmt_identify(long gmt_identify) {
        this.gmt_identify = gmt_identify;
    }

    public long getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(long gmt_create) {
        this.gmt_create = gmt_create;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }
}
