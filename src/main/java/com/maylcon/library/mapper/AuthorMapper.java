package com.maylcon.library.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.maylcon.library.entity.AuthorEntity;
import com.maylcon.library.model.AuthorModel;

@Component
public class AuthorMapper {
    
    public AuthorModel mapToModel(AuthorEntity entity) {
        AuthorModel model = new AuthorModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setStatus(entity.getStatus());
        return model;
    }
    
    public AuthorEntity mapToEntity(AuthorModel model) {
        AuthorEntity entity = new AuthorEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setStatus(model.getStatus());
        return entity;
    }
    
    public AuthorModel mapRow(Map<String, Object> row) throws SQLException {
    	AuthorModel author = new AuthorModel();
    	author.setId(Long.parseLong(row.get("ID").toString()));
        author.setName(row.get("NAME").toString());
        author.setStatus(row.get("STATUS").toString());
        return author;
    }
    
   
}
