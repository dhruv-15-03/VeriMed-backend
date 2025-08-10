package com.example.demo.Classes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String name;
    private String email;
    private String password;
    private Integer age;
    private String gender;
    
    @JsonProperty("role")
    private String role; // Frontend sends "role" field
    
    private Boolean isDocter;
    private String specialization;
    
    public Boolean getIsDocter() {
        if (this.isDocter != null) {
            return this.isDocter;
        }
        return "doctor".equalsIgnoreCase(this.role) || "docter".equalsIgnoreCase(this.role);
    }
}
