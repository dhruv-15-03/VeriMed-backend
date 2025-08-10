package com.example.demo.Controller;

import com.example.demo.Classes.User;
import com.example.demo.Classes.UserDTO;
import com.example.demo.Database.UserAll;
import com.example.demo.Implementation.CustomerUser;
import com.example.demo.Implementation.userImplementation;
import com.example.demo.Response.AuthResponse;
import com.example.demo.Response.Login;
import com.example.demo.config.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/auth", "/"})
public class AuthController {
    @Autowired
    private userImplementation userImplementation;
    @Autowired
    private UserAll userAll;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerUser customerUser;
    @PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
    public AuthResponse add(@RequestBody UserDTO userDto) throws Exception {
        User newUser = new User();

        User check=userAll.searchByEmail(userDto.getEmail());
        if(check!=null){
            throw new Exception("User exist with email :" + userDto.getEmail());
        }
        newUser.setAge(userDto.getAge());
        newUser.setName(userDto.getName());
        newUser.setGender(userDto.getGender());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setIsDocter(userDto.getIsDocter());
        newUser.setSpecialization(userDto.getSpecialization());
        Authentication authentication=new UsernamePasswordAuthenticationToken(newUser.getEmail(),newUser.getPassword());
        String token = JwtProvider.generateToken(authentication);
        userAll.save(newUser);
        return new AuthResponse(token,"Registered Successfully");
    }
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public AuthResponse login(@RequestBody Login login) throws Exception {
        Authentication authentication=authenticate(login.getEmail(),login.getPassword());
        String token = JwtProvider.generateToken(authentication);
        return new AuthResponse(token,"Login Successfully");
    }
    
    // API endpoints for frontend compatibility
    @PostMapping(value = "/api/signup", consumes = "application/json", produces = "application/json")
    public AuthResponse apiSignup(@RequestBody UserDTO userDto) throws Exception {
        return add(userDto); // Reuse the existing signup logic
    }
    
    @PostMapping(value = "/api/login", consumes = "application/json", produces = "application/json") 
    public AuthResponse apiLogin(@RequestBody Login login) throws Exception {
        return login(login); // Reuse the existing login logic
    }

    private Authentication authenticate(String email, String password)  {
        UserDetails userDetails=customerUser.loadUserByUsername(email);
        if(userDetails==null){
            throw new BadCredentialsException("Invalid email");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Password is incorrect");

        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
