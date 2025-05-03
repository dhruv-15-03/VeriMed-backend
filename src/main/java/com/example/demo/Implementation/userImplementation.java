package com.example.demo.Implementation;

import com.example.demo.Classes.User;
import com.example.demo.Database.UserAll;
import com.example.demo.config.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class userImplementation {
    @Autowired
    UserAll userAll;
    public User getFromId(Integer id){
        Optional<User> user= Optional.of((userAll.getReferenceById(id)));
        return user.get();
    }
    public String deleteFromId(Integer id,User user) throws Exception {
        if(!Objects.equals(user.getId(), id)){
            throw new Exception("You are not the user can't delete");
        }
        userAll.deleteById(id);
        return "User deleted with userid "+id ;
    }
    public User getFromJwt(String jwt){
        String userName= JwtProvider.getUserNameFromJwt(jwt);
        return userAll.searchByEmail(userName);
    }
    public UserAll getuSerAll() {
        return userAll;
    }


    public User Update(User user,User user2) throws  Exception {
        User old=userAll.getReferenceById(user.getId());
        if(user2.getName()!=null){
            old.setName(user2.getName());
        }


        userAll.save(old);
        return old;
    }
    public List<User> searchUser(String query){
        return userAll.searchUser(query);
    }

}