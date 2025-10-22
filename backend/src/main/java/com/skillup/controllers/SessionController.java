package com.skillup.controllers;
import com.skillup.entities.Session;
import com.skillup.repositories.SessionRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.*;
@RestController
@RequestMapping("/api/sessions")
@CrossOrigin(origins = {"http://localhost:5173"})
public class SessionController {
  private final SessionRepository repo;
  public SessionController(SessionRepository repo){ this.repo = repo; }
  @GetMapping("/health") public Map<String,String> health(){ return Map.of("status","ok"); }
  @PostMapping public Session create(@RequestBody Session obj){ return repo.save(obj); }
  @GetMapping public List<Session> all(){ return repo.findAll(); }
  @GetMapping("/<built-in function id>") public ResponseEntity<Session> one(@PathVariable Long id){ return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
  @PutMapping("/<built-in function id>") public ResponseEntity<Session> update(@PathVariable Long id, @RequestBody Session in){ return repo.findById(id).map(e -> { in.setId(id); return ResponseEntity.ok(repo.save(in)); }).orElse(ResponseEntity.notFound().build()); }
  @DeleteMapping("/<built-in function id>") public ResponseEntity<Void> delete(@PathVariable Long id){ if(!repo.existsById(id)) return ResponseEntity.notFound().build(); repo.deleteById(id); return ResponseEntity.noContent().build(); }
}