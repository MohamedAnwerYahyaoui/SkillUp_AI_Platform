package com.skillup.controllers;

import com.skillup.entities.Apprenant;
import com.skillup.repositories.ApprenantRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
@RequestMapping("/api/apprenants")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:8000"})
public class ApprenantController {
  private final ApprenantRepository repo;

  public ApprenantController(ApprenantRepository repo) {
    this.repo = repo;
  }

  @GetMapping("/health")
  public Map<String, String> health() {
    return Map.of("status", "ok");
  }

  @PostMapping
  public Apprenant create(@RequestBody Apprenant apprenant) {
    return repo.save(apprenant);
  }

  @GetMapping
  public List<Apprenant> all() {
    return repo.findAll();
  }

  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Apprenant> one(@PathVariable("id") Long id) {
    return repo.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Apprenant> update(@PathVariable Long id, @RequestBody Apprenant in) {
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
