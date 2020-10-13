package com.semi.controllers;

import com.semi.dto.AuthorDto;
import com.semi.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @RequestMapping(value = "/authors", method = RequestMethod.GET)
    public ResponseEntity<List<AuthorDto>> findAuthors() {
        List<AuthorDto> authorDtos = authorService.findAll();
        return ResponseEntity.ok(authorDtos);
    }
}
