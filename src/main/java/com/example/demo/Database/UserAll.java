package com.example.demo.Database;

import com.example.demo.Classes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserAll extends JpaRepository<User,Integer> {
    @Query("SELECT u FROM User u where u.email LIKE (:query)")
   public User searchByEmail(@Param("query") String email);
    @Query("select u from User u where u.name like %:query or u.email like %:query%")
    public List<User> searchUser(@Param("query") String query);
}
