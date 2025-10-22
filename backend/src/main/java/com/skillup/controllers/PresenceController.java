package com.skillup.controllers;
import com.skillup.entities.Presence;
import com.skillup.repositories.PresenceRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.*;
@RestController
@RequestMapping("/api/presences")
@CrossOrigin(origins = {"http://localhost:5173"})
public class PresenceController {
  private final PresenceRepository repo;
  public PresenceController(PresenceRepository repo){ this.repo = repo; }
  @GetMapping("/health") public Map<String,String> health(){ return Map.of("status","ok"); }
  @PostMapping public Presence create(@RequestBody Presence obj){ return repo.save(obj); }
  @GetMapping public List<Presence> all(){ return repo.findAll(); }
  @GetMapping("/<built-in function id>") public ResponseEntity<Presence> one(@PathVariable Long id){ return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
  @PutMapping("/<built-in function id>") public ResponseEntity<Presence> update(@PathVariable Long id, @RequestBody Presence in){ return repo.findById(id).map(e -> { in.setId(id); return ResponseEntity.ok(repo.save(in)); }).orElse(ResponseEntity.notFound().build()); }
  @DeleteMapping("/<built-in function id>") public ResponseEntity<Void> delete(@PathVariable Long id){ if(!repo.existsById(id)) return ResponseEntity.notFound().build(); repo.deleteById(id); return ResponseEntity.noContent().build(); }
}