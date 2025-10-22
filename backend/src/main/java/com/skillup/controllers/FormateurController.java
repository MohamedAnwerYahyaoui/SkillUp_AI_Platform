package com.skillup.controllers;
import com.skillup.entities.Formateur;
import com.skillup.repositories.FormateurRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.*;
@RestController
@RequestMapping("/api/formateurs")
@CrossOrigin(origins = {"http://localhost:5173"})
public class FormateurController {
  private final FormateurRepository repo;
  public FormateurController(FormateurRepository repo){ this.repo = repo; }
  @GetMapping("/health") public Map<String,String> health(){ return Map.of("status","ok"); }
  @PostMapping public Formateur create(@RequestBody Formateur obj){ return repo.save(obj); }
  @GetMapping public List<Formateur> all(){ return repo.findAll(); }
  @GetMapping("/<built-in function id>") public ResponseEntity<Formateur> one(@PathVariable Long id){ return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
  @PutMapping("/<built-in function id>") public ResponseEntity<Formateur> update(@PathVariable Long id, @RequestBody Formateur in){ return repo.findById(id).map(e -> { in.setId(id); return ResponseEntity.ok(repo.save(in)); }).orElse(ResponseEntity.notFound().build()); }
  @DeleteMapping("/<built-in function id>") public ResponseEntity<Void> delete(@PathVariable Long id){ if(!repo.existsById(id)) return ResponseEntity.notFound().build(); repo.deleteById(id); return ResponseEntity.noContent().build(); }
}