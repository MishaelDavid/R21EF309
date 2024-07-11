package com.calci.avgCalci.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
@RestController
@RequestMapping("/numbers")
public class NumberController {
    @Autowired
    private NumberService numberService;
    @GetMapping("/{type}")
    public ResponseEntity<Map<String, Object>> getNumbers(@PathVariable String type) {
        if (!"pfe".contains(type) && !type.equals("r")) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid type"));
        }
        Map<String, Object> response = numberService.fetch(type);
        return ResponseEntity.ok(response);
    }
}
