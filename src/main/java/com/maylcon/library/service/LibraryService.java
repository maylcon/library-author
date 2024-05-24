package com.maylcon.library.service;

import java.util.List;

import com.maylcon.library.model.LibraryModel;

public interface LibraryService {
	public LibraryModel FindById(Long id);
	public List<LibraryModel> FindAll();
    public boolean Save(LibraryModel author);
    public List<LibraryModel> GetByAuthor(Long authorName);
    public void DeleteById(Long id);
    public LibraryModel Update(Long id, LibraryModel author);
}
