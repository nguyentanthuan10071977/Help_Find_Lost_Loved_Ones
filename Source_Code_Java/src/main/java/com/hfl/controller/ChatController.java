package com.hfl.controller;
import com.hfl.model.ChatMessage; import com.hfl.repository.ChatMessageRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/chat")
public class ChatController {
 private final ChatMessageRepository repo; public ChatController(ChatMessageRepository repo){this.repo=repo;}
 @GetMapping("/{userId}") public List<ChatMessage> history(@PathVariable Long userId){return repo.findByUserIdOrderByCreatedAtAsc(userId);}
 @PostMapping("/{userId}") public ResponseEntity<ChatMessage> send(@PathVariable Long userId,@RequestBody ChatMessage m){m.setId(null);m.setUserId(userId);if(m.getSenderRole()==null)m.setSenderRole("USER");return ResponseEntity.status(201).body(repo.save(m));}
}
