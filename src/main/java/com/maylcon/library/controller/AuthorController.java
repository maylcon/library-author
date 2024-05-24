package com.maylcon.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maylcon.library.model.AuthorModel;
import com.maylcon.library.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
    @GetMapping("/getById/{authorId}")
    public ResponseEntity<AuthorModel> getById(@PathVariable Long authorId) {
    	AuthorModel model = new AuthorModel();
    	model = authorService.FindById(authorId);
    	if(model != null) {
    		return new ResponseEntity<>(model, HttpStatus.OK);
    	}
        return new ResponseEntity<>(model, HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<List<AuthorModel>> getAll() {
    	return new ResponseEntity<>(authorService.FindAll(), HttpStatus.OK);
    }
    
    @GetMapping("/getByName/{authorName}")
    public ResponseEntity<List<AuthorModel>> getByName(@PathVariable String authorName) {
    	return new ResponseEntity<>(authorService.GetByName(authorName), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody AuthorModel author) {
        authorService.Save(author);
        return new ResponseEntity<>("Author created successfully", HttpStatus.CREATED);
    }
    
    @PutMapping("/update")
    public ResponseEntity<AuthorModel> update(@RequestParam Long id, @RequestBody AuthorModel author) {
    	AuthorModel isUpdate = authorService.Update(id,author);
    	if(isUpdate != null) {
        	return new ResponseEntity<>(isUpdate, HttpStatus.OK);
    	}
        return new ResponseEntity<>(isUpdate, HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping("/deleteById/{authorId}")
    public ResponseEntity<String> deleteById(@PathVariable Long authorId) {
    	boolean isEliminated = authorService.DeleteById(authorId);
    	if(isEliminated) {
    		return new ResponseEntity<>("Author eliminated successfully", HttpStatus.OK);
    	}
        return new ResponseEntity<>("Author can not eliminated", HttpStatus.NOT_FOUND);
    }
    
}
