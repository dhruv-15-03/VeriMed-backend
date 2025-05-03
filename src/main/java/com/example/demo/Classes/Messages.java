package com.example.demo.Classes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer msgId;
    private LocalDateTime time;
    private String message;
    private String image;
    private Integer chatId;
    @ManyToOne
    private User user;

    public Messages() {
    }

    public Messages(Integer msgId, LocalDateTime time, String message,String image) {
        this.msgId = msgId;
        this.time = time;
        this.message = message;
        this.image=image;
    }
}

