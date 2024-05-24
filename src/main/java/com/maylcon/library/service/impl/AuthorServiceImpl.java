package com.maylcon.library.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.maylcon.library.entity.AuthorEntity;
import com.maylcon.library.entity.LibraryEntity;
import com.maylcon.library.mapper.AuthorMapper;
import com.maylcon.library.model.AuthorModel;
import com.maylcon.library.repository.AuthorRepository;
import com.maylcon.library.repository.LibraryRepository;
import com.maylcon.library.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private LibraryRepository libraryRepository;
	
	@Autowired
	private AuthorMapper authorMapper;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

    public AuthorModel FindById(Long id) {
        AuthorEntity entity = authorRepository.findById(id).orElse(null);
        if (entity != null) {
            return authorMapper.mapToModel(entity);
        }
        return null;
    }
    
    public List<AuthorModel> FindAll() {
        List<AuthorEntity> entities = authorRepository.findAll();
        List<AuthorModel> models = new ArrayList<>();

        for (AuthorEntity entity : entities) {
            AuthorModel model = authorMapper.mapToModel(entity);
            models.add(model);
        }

        return models;
    }
    
    public void Save(AuthorModel author) {
        authorRepository.save(authorMapper.mapToEntity(author));
    }
    
    
    public List<AuthorModel> GetByName(String authorName) {
    	
    	SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("get_author_by_name");

        MapSqlParameterSource inParams = new MapSqlParameterSource()
                .addValue("p_author_name", authorName);

        Map<String, Object> resultMap = jdbcCall.execute(inParams);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) resultMap.get("P_CURSOR");

        List<AuthorModel> authors = new ArrayList<>();
        for (Map<String, Object> row : resultList) {
            AuthorModel author = new AuthorModel();
            try {
				author = authorMapper.mapRow(row);
			} catch (SQLException e) {
				e.printStackTrace();
			}
            authors.add(author);
        }
        
        return authors;
    }
    
    
    public boolean DeleteById(Long id) {
    	boolean hasRelatedRecords = checkRelatedRecords(id);
        if (!hasRelatedRecords) {
            authorRepository.deleteById(id);
        }
        return !hasRelatedRecords;
    }
    
    private boolean checkRelatedRecords(Long authorId) {
    	List<LibraryEntity> relatedRecords = libraryRepository.findByAuthorId(authorId);
        return !relatedRecords.isEmpty();
    }
    
    public AuthorModel Update(Long id, AuthorModel author) {
    	AuthorEntity entity = authorRepository.findById(id).orElse(null);
        if (entity != null) {
        	author.setId(id);
        	authorRepository.save(authorMapper.mapToEntity(author));
        	return author;
        }
        return null;
    	
    } 
    
}
