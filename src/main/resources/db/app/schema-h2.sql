
CREATE TABLE AUTHOR (
    AUTHOR_ID       BIGINT IDENTITY NOT NULL PRIMARY KEY,
    AUTHOR_NAME     VARCHAR(100) NOT NULL,
    CONSTRAINT AUTHOR_NAME_UN UNIQUE (AUTHOR_NAME)
);

CREATE TABLE BOOK (
    BOOK_ID         BIGINT IDENTITY NOT NULL PRIMARY KEY,
    BOOK_TITLE      VARCHAR(200) NOT NULL,
    BOOK_ISBN       VARCHAR(10) DEFAULT NULL,
    BOOK_LANGUAGE   VARCHAR(2) DEFAULT 'EN',
    BOOK_COVER      VARCHAR(50) DEFAULT NULL,
    CONSTRAINT BOOK_ISBN_UN UNIQUE (BOOK_ISBN),
    CONSTRAINT BOOK_LANGUAGE_CHK CHECK (BOOK_LANGUAGE IN ('EN', 'RU'))
);

CREATE TABLE BOOK_AUTHOR (
    AUTHOR_ID       BIGINT NOT NULL,
    BOOK_ID         BIGINT NOT NULL,
    CONSTRAINT BOOK_AUTHOR_PK PRIMARY KEY (BOOK_ID, AUTHOR_ID),
    CONSTRAINT BOOK_AUTHOR_BOOK_FK FOREIGN KEY (BOOK_ID) REFERENCES BOOK (BOOK_ID),
    CONSTRAINT BOOK_AUTHOR_AUTHOR_FK FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHOR (AUTHOR_ID)
);
