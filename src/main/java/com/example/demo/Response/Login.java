package com.example.demo.Response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Login {
     private String email;
   private String password;

    public Login() {
    }

    public Login(String username, String password) {
        this.email = username;
        this.password = password;
    }

}
