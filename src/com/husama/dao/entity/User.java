package com.husama.dao.entity;

import static com.husama.constant.UserRoles.*;

/**
 * Created by husama on 16-9-26.
 */
public class User extends BaseEntity{

    private String email;

    private String nickname;

    private String password;

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
