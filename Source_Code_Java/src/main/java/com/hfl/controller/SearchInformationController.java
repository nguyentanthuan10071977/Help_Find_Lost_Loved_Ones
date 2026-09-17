package com.hfl.controller;
import com.hfl.model.SearchInformation; import com.hfl.repository.SearchInformationRepository; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/search-information")
public class SearchInformationController {
 private final SearchInformationRepository repo; public SearchInformationController(SearchInformationRepository repo){this.repo=repo;}
 @GetMapping public List<SearchInformation> all(){return repo.findAll();}
 @PostMapping public ResponseEntity<SearchInformation> add(@RequestBody SearchInformation s){s.setId(null);return ResponseEntity.status(201).body(repo.save(s));}
 @PutMapping("/{id}") public ResponseEntity<SearchInformation> edit(@PathVariable Long id,@RequestBody SearchInformation s){return repo.findById(id).map(x->{s.setId(id);return ResponseEntity.ok(repo.save(s));}).orElse(ResponseEntity.notFound().build());}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
