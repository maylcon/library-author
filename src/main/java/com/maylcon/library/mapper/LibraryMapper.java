package com.maylcon.library.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maylcon.library.entity.LibraryEntity;
import com.maylcon.library.model.LibraryModel;

@Component
public class LibraryMapper {
    
	@Autowired
    private AuthorMapper authorMapper;
    
    public LibraryModel mapToModel(LibraryEntity entity) {
        LibraryModel model = new LibraryModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setCategory(entity.getCategory());
        model.setStatus(entity.getStatus());
        model.setAuthor(authorMapper.mapToModel(entity.getAuthor()));
        return model;
    }
    
    public LibraryEntity mapToEntity(LibraryModel model) {
        LibraryEntity entity = new LibraryEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setCategory(model.getCategory());
        entity.setStatus(model.getStatus());
        entity.setAuthor(authorMapper.mapToEntity(model.getAuthor()));
        return entity;
    }
    
    public LibraryModel mapRow(Map<String, Object> row) throws SQLException {
    	LibraryModel library = new LibraryModel();
    	library.setId(Long.parseLong(row.get("ID").toString()));
    	library.setName(row.get("NAME").toString());
    	library.setCategory(row.get("CATEGORY").toString());
    	library.setStatus(row.get("STATUS").toString());
        return library;
    }
}