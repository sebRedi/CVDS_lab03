package edu.eci.cvds.tdd.library.book;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class BookTest {


    @Test
    public void shouldKnowHisOwnParameters(){
        String title = "Título";
        String author = "Julian";
        String isbn = "isbn";
        Book myBook = new Book(title, author, isbn);
        assertEquals(myBook.getTittle(), title);
        assertEquals(myBook.getAuthor(), author);
        assertEquals(myBook.getIsbn(), isbn);
    }

    @Test
    public void shouldKnowIfItsEqualToAnotherOne(){
        String title = "Título";
        String author = "Julian";
        String isbn = "isbn";
        Book myBook = new Book(title, author, isbn);
        Book otherBook = myBook;
        assertEquals(myBook, otherBook);
    }

}