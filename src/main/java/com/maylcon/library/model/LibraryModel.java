package com.maylcon.library.model;

import lombok.Data;

@Data
public class LibraryModel {
    private Long id;
    private String name;
    private String category;
    private String status;
    private AuthorModel author;
}