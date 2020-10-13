package com.semi.repos;

import com.semi.dto.AuthorDto;

import java.util.List;

public interface AuthorRepository {

    List<AuthorDto> getAllAuthors ();
}
