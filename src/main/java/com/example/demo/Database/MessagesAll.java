package com.example.demo.Database;

import com.example.demo.Classes.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MessagesAll extends JpaRepository<Messages,Integer> {
}
