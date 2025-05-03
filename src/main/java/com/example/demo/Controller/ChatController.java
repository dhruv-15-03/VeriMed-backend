package com.example.demo.Controller;

import com.example.demo.Classes.Chats;
import com.example.demo.Classes.User;
import com.example.demo.Database.ChatsAll;
import com.example.demo.Database.UserAll;
import com.example.demo.Implementation.chatImplementations;
import com.example.demo.Implementation.userImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    @Autowired
    private UserAll userAll;
    @Autowired
    private ChatsAll chatAll;
    @Autowired
    private userImplementation userImplementation;
    @Autowired
    private chatImplementations chatMethod;
    @PostMapping("/newChat/{userId}")
    public Chats addSpecific(@RequestHeader ("Authorization") String jwt,@RequestBody Chats chat,@PathVariable Integer userId){
        User us2=userAll.getReferenceById(userId);
        User user=userImplementation.getFromJwt(jwt);
        return chatMethod.add(chat,user,us2);
    }
    @GetMapping("/findAll")
    public List<Chats> find(@RequestHeader ("Authorization") String jwt){
        User user=userImplementation.getFromJwt(jwt);
        return chatMethod.findByUser(user,userAll.getReferenceById(user.getId()));
    }
    @DeleteMapping("/deleteForAll/{userId}")
    public String delete(@RequestHeader ("Authorization") String jwt, @PathVariable Integer userId) throws  Exception {
        User user=userImplementation.getFromJwt(jwt);
        return chatMethod.delete(user,userAll.getReferenceById(userId));
    }
    @DeleteMapping("/deleteByIdForAll/{chatId}")
    public String deleteById(@RequestHeader ("Authorization") String jwt, @PathVariable Integer chatId) throws  Exception {
        User user=userImplementation.getFromJwt(jwt);
        return chatMethod.deleteById(user,chatId);
    }
    @DeleteMapping("/deleteForMe/{chatId}")
    public String deleteForMe(@RequestHeader ("Authorization") String jwt,@PathVariable Integer chatId) throws  Exception {
        User user=userImplementation.getFromJwt(jwt);
        return chatMethod.deleteForMe(user,chatId);
    }
    @GetMapping("/chatAll")
    public List<Chats> all(@RequestHeader ("Authorization") String jwt){
        User user=userImplementation.getFromJwt(jwt);
        return chatAll.findAll();
    }

}