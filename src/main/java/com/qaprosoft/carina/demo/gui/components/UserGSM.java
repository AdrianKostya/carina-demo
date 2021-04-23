package com.qaprosoft.carina.demo.gui.components;

public class UserGSM {
    private String nickname;
    private String email ;
    private String password;

    public UserGSM(String nickname, String email, String password){
        this.nickname= nickname;
        this.email = email;
        this.password=password;
    }

    public UserGSM(){}

    public String getNickname(){
        return nickname;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
