package com.semi.repos;

import com.semi.dto.AuthorDto;
import com.semi.tables.AuthorBook;
import com.semi.tables.Book;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.semi.tables.Author.AUTHOR;
import static com.semi.tables.AuthorBook.AUTHOR_BOOK;
import static com.semi.tables.Book.BOOK;

@Component
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {

    public final DSLContext dslContext;

    @Override
    public List<AuthorDto> getAllAuthors() {
        return dslContext.selectFrom(AUTHOR)
                .fetchInto(AuthorDto.class);
    }

    @Override
    public AuthorDto findAuthorByBookTitle(String bootTitle) {
        Result<Record> records = dslContext.select()
                .from(AUTHOR
                        .leftJoin(AUTHOR_BOOK).on(AUTHOR.ID.eq(AUTHOR_BOOK.BOOK_ID))
                        .leftJoin(BOOK).on(AUTHOR_BOOK.BOOK_ID.eq(BOOK.ID))
                )
                .where(BOOK.TITLE.eq(bootTitle))
                .fetch();
        return records.isEmpty() ? null : records.get(0).into(AuthorDto.class);
    }
}
