package com.skillup.controllers;
import org.springframework.web.bind.annotation.*; import java.util.Map;
@RestController @RequestMapping("/api")
@CrossOrigin(origins={"http://localhost:5173"})
public class HealthController {
  @GetMapping("/health") public Map<String,String> health(){ return Map.of("service","backend","status","ok"); }
}