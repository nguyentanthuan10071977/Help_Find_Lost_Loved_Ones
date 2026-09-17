package com.hfl.controller;
import com.hfl.model.FindRelativeRequest; import com.hfl.repository.FindRelativeRequestRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/find-relatives")
public class FindRelativeController {
 private final FindRelativeRequestRepository repo; public FindRelativeController(FindRelativeRequestRepository repo){this.repo=repo;}
 @PostMapping public ResponseEntity<FindRelativeRequest> register(@RequestBody FindRelativeRequest r){r.setId(null);r.setStatus("PENDING");return ResponseEntity.status(201).body(repo.save(r));}
 @GetMapping public List<FindRelativeRequest> all(){return repo.findAll();}
 @PutMapping("/{id}/status") public ResponseEntity<FindRelativeRequest> status(@PathVariable Long id,@RequestParam String value){return repo.findById(id).map(r->{r.setStatus(value);return ResponseEntity.ok(repo.save(r));}).orElse(ResponseEntity.notFound().build());}
}
