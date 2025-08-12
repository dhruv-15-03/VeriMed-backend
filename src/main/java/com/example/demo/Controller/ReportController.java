package com.example.demo.Controller;

import com.example.demo.Classes.ReportRequest;
import com.example.demo.Classes.User;
import com.example.demo.Database.UserAll;
import com.example.demo.config.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping({"/api/reports", "/reports"})
public class ReportController {

    @Autowired
    private UserAll userAll;

    // Append a report entry to user's summary with timestamp; newest first
    @PostMapping
    public ResponseEntity<List<String>> addReport(@RequestHeader(name = "Authorization", required = false) String authHeader,
                                                  @RequestBody ReportRequest request) throws Exception {
        if (authHeader == null || authHeader.isBlank()) {
            return ResponseEntity.status(401).build();
        }
        String email = JwtProvider.getUserNameFromJwt(authHeader);
        User user = userAll.searchByEmail(email);
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        if (request == null || request.getReport() == null || request.getReport().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        List<String> summary = user.getSummary();
        if (summary == null) summary = new ArrayList<>();
        String entry = DateTimeFormatter.ISO_INSTANT.format(Instant.now()) + "|ADD|" + request.getReport();
        summary.add(0, entry); // newest at index 0
        user.setSummary(summary);
        userAll.save(user);
        return ResponseEntity.ok(summary);
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<List<String>> deleteReport(@RequestHeader(name = "Authorization", required = false) String authHeader,
                                                     @PathVariable int index) throws Exception {
        if (authHeader == null || authHeader.isBlank()) {
            return ResponseEntity.status(401).build();
        }
        String email = JwtProvider.getUserNameFromJwt(authHeader);
        User user = userAll.searchByEmail(email);
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        List<String> summary = user.getSummary();
        if (summary == null) summary = new ArrayList<>();
        if (index < 0 || index >= summary.size()) {
            return ResponseEntity.badRequest().build();
        }
        String removed = summary.get(index);
        String log = DateTimeFormatter.ISO_INSTANT.format(Instant.now()) + "|DELETE|" + removed;
        summary.add(0, log);
        user.setSummary(summary);
        userAll.save(user);
        return ResponseEntity.ok(summary);
    }

    @GetMapping
    public ResponseEntity<List<String>> listReports(@RequestHeader(name = "Authorization", required = false) String authHeader) throws Exception {
        if (authHeader == null || authHeader.isBlank()) {
            return ResponseEntity.status(401).build();
        }
        String email = JwtProvider.getUserNameFromJwt(authHeader);
        User user = userAll.searchByEmail(email);
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        List<String> summary = user.getSummary();
        if (summary == null) summary = new ArrayList<>();
        summary.sort(Comparator.comparing((String s) -> s.split("\\|", 2)[0]).reversed());
        return ResponseEntity.ok(summary);
    }
}
