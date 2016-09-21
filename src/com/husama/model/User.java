package com.husama.model;

import com.husama.annotation.dao.Persistence;

/**
 * Created by husama on 16-8-28.
 */
public class User extends BaseModel{


    public static final String ROLE_ADMIN = "ADMIN";

    public static final String ROLE_USER = "USER";

    @Persistence
    private String email;

    @Persistence
    private String nickname;

    @Persistence
    private String password;

    @Persistence
    private String role = ROLE_USER;

    public User(String email, String password,String role){
        this.email= email;
        this.password = password;
        this.role = role;
    }

    public User(){

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
