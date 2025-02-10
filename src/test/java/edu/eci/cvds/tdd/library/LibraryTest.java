package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    @Test
    public void shouldIncreaseTheAmountInAddBook(){
        Book testBook = new Book("100 años de soledad", "Gabriel Garcia", "1000");
        Library library = new Library();
        library.addBook(testBook);
        assertEquals(1,library.books().size());
        }

    @Test
    public void shouldAddBookToLibrary(){
        Book testBook = new Book("100 años de soledad", "Gabriel Garcia", "1000");
        Library library = new Library();
        library.addBook(testBook);
        assertEquals(testBook, library.books().get(0));
    }


}