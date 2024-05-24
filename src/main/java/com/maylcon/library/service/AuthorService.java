package com.maylcon.library.service;

import java.util.List;

import com.maylcon.library.model.AuthorModel;

public interface AuthorService {
	public AuthorModel FindById(Long id);
	public List<AuthorModel> FindAll();
    public void Save(AuthorModel author);
    public List<AuthorModel> GetByName(String authorName);
    public boolean DeleteById(Long id);
    public AuthorModel Update(Long id, AuthorModel author);
}
