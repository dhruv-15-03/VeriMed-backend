package com.example.demo.Services;

import com.example.demo.Classes.Chats;
import com.example.demo.Classes.User;

import java.util.List;

public interface ChatsService {
    public Chats add(Chats chat, User user1, User user2);
    public List<Chats> findByUser(User user1, User user2);
    public String delete(User user1,User user2) throws Exception;
    public String deleteById(User user,int chatId) throws Exception;
    public String deleteForMe(User user,int chatId) throws Exception;
    public Chats Update(User user,Chats chat) throws Exception;
}
