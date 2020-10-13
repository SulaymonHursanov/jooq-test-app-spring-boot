package com.semi.repos;

import com.semi.Public;
import com.semi.dto.AuthorDto;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {

    public final DSLContext dslContext;

    @Override
    public List<AuthorDto> getAllAuthors() {
        return dslContext.selectFrom(Public.PUBLIC.AUTHOR)
                .fetchInto(AuthorDto.class);
    }
}
