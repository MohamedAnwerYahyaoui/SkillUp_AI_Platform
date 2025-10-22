package com.skillup.controllers;
import com.skillup.entities.Inscription;
import com.skillup.repositories.InscriptionRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.*;
@RestController
@RequestMapping("/api/inscriptions")
@CrossOrigin(origins = {"http://localhost:5173"})
public class InscriptionController {
  private final InscriptionRepository repo;
  public InscriptionController(InscriptionRepository repo){ this.repo = repo; }
  @GetMapping("/health") public Map<String,String> health(){ return Map.of("status","ok"); }
  @PostMapping public Inscription create(@RequestBody Inscription obj){ return repo.save(obj); }
  @GetMapping public List<Inscription> all(){ return repo.findAll(); }
  @GetMapping("/<built-in function id>") public ResponseEntity<Inscription> one(@PathVariable Long id){ return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
  @PutMapping("/<built-in function id>") public ResponseEntity<Inscription> update(@PathVariable Long id, @RequestBody Inscription in){ return repo.findById(id).map(e -> { in.setId(id); return ResponseEntity.ok(repo.save(in)); }).orElse(ResponseEntity.notFound().build()); }
  @DeleteMapping("/<built-in function id>") public ResponseEntity<Void> delete(@PathVariable Long id){ if(!repo.existsById(id)) return ResponseEntity.notFound().build(); repo.deleteById(id); return ResponseEntity.noContent().build(); }
}