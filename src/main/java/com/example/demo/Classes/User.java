package com.example.demo.Classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Enabled
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Boolean isDocter;
    private Integer Age;
    private String gender;
    private String specialization;
    private String[] summary;
    public User( String name, String email, String password, Boolean isDocter,Integer Age, String gender) {
        this.email=email;
        this.password=password;
        this.isDocter=isDocter;
        this.name=name;
        this.Age=Age;
        this.gender=gender;
    }
    @ManyToMany(mappedBy = "chats")
    @JsonManagedReference
    private List<Chats> chat=new ArrayList<>();

}
