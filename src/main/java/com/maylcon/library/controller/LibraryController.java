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

import com.maylcon.library.model.LibraryModel;
import com.maylcon.library.service.LibraryService;

@RestController
@RequestMapping("/library")
public class LibraryController {

	@Autowired
	private LibraryService libraryService;
	
    @GetMapping("/getById/{libraryId}")
    public ResponseEntity<LibraryModel> getById(@PathVariable Long libraryId) {
    	LibraryModel model = new LibraryModel();
    	model = libraryService.FindById(libraryId);
    	if(model != null) {
    		return new ResponseEntity<>(model, HttpStatus.OK);
    	}
        return new ResponseEntity<>(model, HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<List<LibraryModel>> getAll() {
    	return new ResponseEntity<>(libraryService.FindAll(), HttpStatus.OK);
    }
    
    @GetMapping("/getByAuthor/{authorId}")
    public ResponseEntity<List<LibraryModel>> getByName(@PathVariable Long authorId) {
    	return new ResponseEntity<>(libraryService.GetByAuthor(authorId), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody LibraryModel author) {
        boolean isCreate = libraryService.Save(author);
        if(isCreate) {
        	return new ResponseEntity<>("Library created successfully", HttpStatus.OK);
    	}
        return new ResponseEntity<>("Error to created the library", HttpStatus.NOT_FOUND);
    }
    
    @PutMapping("/update")
    public ResponseEntity<LibraryModel> update(@RequestParam Long id, @RequestBody LibraryModel library) {
    	LibraryModel isUpdate = libraryService.Update(id,library);
    	if(isUpdate != null) {
        	return new ResponseEntity<>(isUpdate, HttpStatus.OK);
    	}
        return new ResponseEntity<>(isUpdate, HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/deleteById/{libraryId}")
    public ResponseEntity<String> deleteById(@PathVariable Long libraryId) {
    	libraryService.DeleteById(libraryId);
    	return new ResponseEntity<>("Author eliminated successfully", HttpStatus.OK);
    }
}
