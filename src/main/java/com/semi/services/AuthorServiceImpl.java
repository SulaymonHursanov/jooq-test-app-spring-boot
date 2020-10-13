package com.semi.services;

import com.semi.dto.AuthorDto;
import com.semi.repos.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<AuthorDto> findAll() {
        List<AuthorDto> allAuthors = authorRepository.getAllAuthors();
        log.debug("found authors: {}", allAuthors.size());
        return allAuthors;
    }

    @Override
    public AuthorDto findAuthorByBookName(String bookTitle) {
        AuthorDto author = authorRepository.findAuthorByBookTitle(bookTitle);
        log.debug("found author: {}", author);
        return author;
    }
}
