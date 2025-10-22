package com.skillup.controllers;

import com.skillup.entities.Formation;
import com.skillup.repositories.FormationRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
@RequestMapping("/api/formations")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:8000"})
public class FormationController {
  private final FormationRepository repo;

  public FormationController(FormationRepository repo) {
    this.repo = repo;
  }

  @GetMapping("/health")
  public Map<String, String> health() {
    return Map.of("status", "ok");
  }

  @PostMapping
  public Formation create(@RequestBody Formation obj) {
    return repo.save(obj);
  }

  @GetMapping
  public List<Formation> all() {
    return repo.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Formation> one(@PathVariable Long id) {
    return repo.findById(id).map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Formation> update(@PathVariable Long id, @RequestBody Formation in) {
    return repo.findById(id).map(e -> {
      in.setId(id);
      return ResponseEntity.ok(repo.save(in));
    }).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (!repo.existsById(id)) return ResponseEntity.notFound().build();
    repo.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
