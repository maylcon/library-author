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
import com.maylcon.library.mapper.LibraryMapper;
import com.maylcon.library.model.LibraryModel;
import com.maylcon.library.repository.AuthorRepository;
import com.maylcon.library.repository.LibraryRepository;
import com.maylcon.library.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private LibraryRepository libraryRepository;
	
	@Autowired
	private LibraryMapper libraryMapper;
	
	@Autowired
	private AuthorMapper authorMapper;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

    public LibraryModel FindById(Long id) {
        LibraryEntity entity = libraryRepository.findById(id).orElse(null);
        if (entity != null) {
            return libraryMapper.mapToModel(entity);
        }
        return null;
    }
    
    public List<LibraryModel> FindAll() {
        List<LibraryEntity> entities = libraryRepository.findAll();
        List<LibraryModel> models = new ArrayList<>();

        for (LibraryEntity entity : entities) {
        	LibraryModel model = libraryMapper.mapToModel(entity);
            models.add(model);
        }

        return models;
    }
    
    public boolean Save(LibraryModel author) {
    	boolean hasRelatedRecords = checkRelatedRecords(author.getAuthor().getId());
    	if (hasRelatedRecords) {
    		libraryRepository.save(libraryMapper.mapToEntity(author));
    	}
    	return hasRelatedRecords;
    }
    
    private boolean checkRelatedRecords(Long authorId) {
    	AuthorEntity relatedRecords = authorRepository.findById(authorId).orElse(null);
        return relatedRecords != null;
    }
    
    
    public List<LibraryModel> GetByAuthor(Long authorId) {
    	
    	SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("get_libraries_by_author");

        MapSqlParameterSource inParams = new MapSqlParameterSource()
                .addValue("p_author_id", authorId);

        Map<String, Object> resultMap = jdbcCall.execute(inParams);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) resultMap.get("P_CURSOR");

        List<LibraryModel> libraries = new ArrayList<>();
        for (Map<String, Object> row : resultList) {
        	LibraryModel library = new LibraryModel();
            try {
				library = libraryMapper.mapRow(row);
			} catch (SQLException e) {
				e.printStackTrace();
			}
            library.setAuthor(authorMapper.mapToModel(authorRepository.findById(authorId).orElse(null)) );
            libraries.add(library);
        }
        
        return libraries;
    }
    
    
    public void DeleteById(Long id) {
        libraryRepository.deleteById(id);
    }
    
    public LibraryModel Update(Long id, LibraryModel library) {
    	LibraryEntity entity = libraryRepository.findById(id).orElse(null);
    	AuthorEntity authorEntity = authorRepository.findById(library.getAuthor().getId()).orElse(null);
        if (entity != null && authorEntity != null ) {
        	library.setId(id);
        	libraryRepository.save(libraryMapper.mapToEntity(library));
        	return library;
        }
        return null;
    	
    } 
}
