package com.example.demo.Implementation;

import com.example.demo.Classes.Chats;
import com.example.demo.Classes.User;
import com.example.demo.Database.ChatsAll;
import com.example.demo.Database.UserAll;
import com.example.demo.Services.ChatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class chatImplementations implements ChatsService {
    @Autowired
    private ChatsAll chatAll;
    @Autowired
    private UserAll userAll;
    @Override
    public Chats add(Chats chat, User user1, User user2) {
        chat.setTime(LocalDateTime.now());
        List<User> user=chat.getChats();
        chat.setAdmin(user1);
        user.add(user2);
        chat.setChats(user);
        List<Chats> temp1=user1.getChat();
        temp1.add(chat);
        user1.setChat(temp1);
        List<Chats> temp2=user2.getChat();
        temp2.add(chat);
        user2.setChat(temp2);
        userAll.save(user1);
        userAll.save(user2);
        chatAll.save(chat);
        return chat;
    }

    @Override
    public List<Chats> findByUser(User user1, User user2) {
        return chatAll.findChatByUser(user2.getEmail());
    }

    @Override
    public String delete(User user1, User user2) throws Exception {
        List<Chats> chats=chatAll.findChatByUser(user2.getEmail());
        for(Chats chat:chats){
            if(chat.getChats().size()==2){
                if(chat.getChats().contains(user1)) {
                    List<Chats> temp=user1.getChat();
                    temp.remove(chat);
                    user1.setChat(temp);
                    List<Chats> temp1=user2.getChat();
                    temp1.remove(chat);
                    user2.setChat(temp1);
                    chatAll.delete(chat);
                    return "Chat deleted Successfully";

                }
            }
        }
        throw new Exception("Can't delete the chat,not a part of it");
    }

    @Override
    public String deleteById(User user, int chatId) throws Exception {
        Chats chat=chatAll.getReferenceById(chatId);
        if(!user.getChat().contains(chat))
            throw new Exception("Not Your chat can't delete");
        for(User temp:chat.getChats()){
            List<Chats> c=temp.getChat();
            c.remove(chat);
            temp.setChat(c);
            userAll.save(temp);
        }
        chatAll.delete(chat);
        return "Deleted Successfully";
    }



    @Override
    public String deleteForMe(User user, int chatId) throws Exception {
        Chats chat=chatAll.getReferenceById(chatId);
        if(!user.getChat().contains(chat)){
            throw new Exception("Can't delete the Chat");
        }
        List<Chats> temp=user.getChat();
        temp.remove(chat);
        user.setChat(temp);
        userAll.save(user);
        return "Successfully Deleted the chat for "+user.getName();
    }



    @Override
    public Chats Update(User user, Chats chat) throws Exception {
        deleteById(user, chat.getChatId());
        return add(chat,user,chat.getChats().get(1));
    }
}