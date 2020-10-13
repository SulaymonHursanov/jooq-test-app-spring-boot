package com.semi.services;

import com.semi.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> findAll();

    AuthorDto findAuthorByBookName(String bookTitle);
}
