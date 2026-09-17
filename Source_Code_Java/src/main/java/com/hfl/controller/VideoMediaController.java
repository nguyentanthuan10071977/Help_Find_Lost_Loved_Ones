package com.hfl.controller;
import com.hfl.model.VideoMedia; import com.hfl.repository.VideoMediaRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/videos")
public class VideoMediaController {
 private final VideoMediaRepository repo; public VideoMediaController(VideoMediaRepository repo){this.repo=repo;}
 @GetMapping public List<VideoMedia> all(){return repo.findAll();}
 @GetMapping("/{id}") public ResponseEntity<VideoMedia> get(@PathVariable Long id){return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());}
 @PostMapping public ResponseEntity<VideoMedia> create(@RequestBody VideoMedia v){return ResponseEntity.status(201).body(repo.save(v));}
 @PutMapping("/{id}") public ResponseEntity<VideoMedia> update(@PathVariable Long id,@RequestBody VideoMedia v){return repo.findById(id).map(x->{v.setId(id);return ResponseEntity.ok(repo.save(v));}).orElse(ResponseEntity.notFound().build());}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
