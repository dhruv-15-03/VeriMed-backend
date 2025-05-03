package com.example.demo.Database;

import com.example.demo.Classes.Chats;
import com.example.demo.Classes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ChatsAll extends JpaRepository<Chats,Integer> {
    @Query("SELECT u from Chats u join u.chats d where d.email=:query OR u.admin.email = :query")
    public List<Chats> findChatByUser(@Param("query") String query);
}
