package com.example.demo.Controller;

import com.example.demo.Classes.User;
import com.example.demo.Database.UserAll;
import com.example.demo.Implementation.userImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserAll userAll;
    @Autowired
    private userImplementation userImplementation;
    @GetMapping("/userAll")
    public List<User> getALl(@RequestHeader ("Authorization") String jwt){
        User user=userImplementation.getFromJwt(jwt);
        List<User> temp=userImplementation.getuSerAll().findAll();
        temp.remove(user);
        return temp;
    }
    @GetMapping("/{query}")
    public List<User> searchUser(@PathVariable ("query") String query){
        return userImplementation.searchUser(query);
    }
    @GetMapping("/getUser/{userId}")
    public User getFromId(@PathVariable Integer userId){
        return userImplementation.getFromId(userId);
    }
    @PutMapping("/update")
    public User update(@RequestHeader ("Authorization") String jwt,@RequestBody User user) throws Exception {
        User main=userImplementation.getFromJwt(jwt);
        return userImplementation.Update(main,user);
    }

    @GetMapping("/delete/{userId}")
    public String delete(@RequestHeader ("Authorization") String jwt,@PathVariable Integer userId) throws Exception {
        User user=userImplementation.getFromJwt(jwt);
        return userImplementation.deleteFromId(userId,user);
    }
    @PutMapping("/profile")
    public User profile(@RequestHeader ("Authorization") String jwt,@RequestBody User user) throws Exception {
        User main=userImplementation.getFromJwt(jwt);
        return userImplementation.Update(main,user);
    }

    @GetMapping("/profile")
    public User getUserFromToken(@RequestHeader ("Authorization") String jwt){
        return userImplementation.getFromJwt(jwt);
    }
}