package com.example.demo.Classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Chats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatId;
    @ManyToOne
    @JsonIgnore
    private User admin;
    @ManyToMany()
    @JsonIgnore
    private List<User> chats=new ArrayList<>();
    private LocalDateTime time;
    @OneToMany
    private List<Messages> message=new ArrayList<>();
    private String theme;

    public Chats() {
    }

    public Chats(Integer chatId, List<User> chats, LocalDateTime time, List<Messages> message, String theme,User admin) {
        this.chatId = chatId;
        this.chats = chats;
        this.time = time;
        this.message = message;
        this.theme = theme;
        this.admin=admin;
    }
}
