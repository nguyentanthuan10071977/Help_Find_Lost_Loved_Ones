package com.hfl.controller;
import com.hfl.model.News; import com.hfl.repository.NewsRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/news")
public class NewsController {
 private final NewsRepository repo; public NewsController(NewsRepository repo){this.repo=repo;}
 @GetMapping public List<News> all(){return repo.findAll();}
 @GetMapping("/search") public List<News> search(@RequestParam String keyword){return repo.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword,keyword);}
 @GetMapping("/{id}") public ResponseEntity<News> get(@PathVariable Long id){return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());}
 @PostMapping public ResponseEntity<News> create(@RequestBody News n){return ResponseEntity.status(201).body(repo.save(n));}
 @PutMapping("/{id}") public ResponseEntity<News> update(@PathVariable Long id,@RequestBody News n){return repo.findById(id).map(x->{n.setId(id);return ResponseEntity.ok(repo.save(n));}).orElse(ResponseEntity.notFound().build());}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
