package com.maylcon.library.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library")
public class LibraryController {
	@GetMapping("/getById")
    public String getById() {
        return "get info by Id";
    }
    
    @GetMapping("/getAll")
    public String getAll() {
        return "get all info";
    }
    
    @GetMapping("/getByName")
    public String getByName() {
        return "get info by name";
    }
    
    @PostMapping("/create")
    public String create() {
        return "create info";
    }
    
    @PutMapping("/update")
    public String update() {
        return "update info";
    }
    
    @DeleteMapping("/deleteById")
    public String deleteById() {
        return "delete info by id";
    }
    
    @DeleteMapping("/deleteAll")
    public String deleteAll() {
        return "delete all info";
    }
}
