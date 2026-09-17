package com.hfl.controller;

import com.hfl.model.Post;
import com.hfl.model.Comment;
import com.hfl.repository.*;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/posts")
public class PostController {
    private final PostRepository posts; private final CommentRepository comments;
    public PostController(PostRepository posts, CommentRepository comments){this.posts=posts;this.comments=comments;}

    @GetMapping public List<Post> all(){return posts.findAll();}
    @GetMapping("/{id}") public ResponseEntity<Post> get(@PathVariable Long id){return posts.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());}
    @GetMapping("/search") public List<Post> search(@RequestParam String keyword){return posts.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword,keyword);}
    @GetMapping("/advanced") public List<Post> advanced(@RequestParam(required=false) Integer age,@RequestParam(required=false) Integer year,@RequestParam(required=false) String circumstance){return posts.advanced(age,year,circumstance);}
    @GetMapping("/lost-time") public List<Post> lostTime(@RequestParam Integer from,@RequestParam Integer to){return posts.findByLostYearBetween(from,to);}
    @GetMapping("/lost-circumstance") public List<Post> circumstance(@RequestParam String value){return posts.findByLostCircumstanceContainingIgnoreCase(value);}
    @GetMapping("/by-object") public List<Post> byObject(@RequestParam String value){return posts.findByObjectInformationContainingIgnoreCase(value);}
    @PostMapping public ResponseEntity<Post> create(@RequestBody @Valid Post p){return ResponseEntity.status(HttpStatus.CREATED).body(posts.save(p));}
    @PutMapping("/{id}") public ResponseEntity<Post> update(@PathVariable Long id,@RequestBody Post p){
        return posts.findById(id).map(x->{p.setId(id); return ResponseEntity.ok(posts.save(p));}).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!posts.existsById(id))return ResponseEntity.notFound().build();posts.deleteById(id);return ResponseEntity.noContent().build();}
    @PostMapping("/{id}/comments") public ResponseEntity<Comment> comment(@PathVariable Long id,@RequestBody Comment c){
        return posts.findById(id).map(p->{c.setId(null);c.setPost(p);return ResponseEntity.status(201).body(comments.save(c));}).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/{id}/comments") public List<Comment> comments(@PathVariable Long id){return comments.findByPostIdOrderByCreatedAtAsc(id);}
}
