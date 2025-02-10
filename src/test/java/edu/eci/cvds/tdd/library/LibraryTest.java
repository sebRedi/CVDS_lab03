package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.user.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    @Test
    public void shouldIncreaseTheAmountInAddBook(){
        Book testBook = new Book("100 años de soledad", "Gabriel Garcia", "1000");
        Library library = new Library();
        // Ver que la cantidad de libros sea 0
        assertEquals(0,library.getQuantityOfBooks());

        library.addBook(testBook);
        // Ver que la cantidad de libros haya aumentado
        assertEquals(1,library.getQuantityOfBooks());

        library.addBook(testBook);
        // Ver que la cantidad de libros disponibles de un libro específico haya aumentado
        assertEquals(2,library.getQuantityOfBooks());
        }

    @Test
    public void shouldAddBookToLibrary(){
        Book testBook = new Book("100 años de soledad", "Gabriel Garcia", "1000");
        Library library = new Library();
        library.addBook(testBook);
        assertTrue(1 >= library.getAvailableQuantityOfBooks(testBook));
    }


    @Test
    public void shouldAddUserToLibrary(){
        User user = new User();
        Library library = new Library();
        library.addUser(user);

        //Ver que la cantidad de usuarios aumente
        assertEquals(1, library.getQuantityOfUsers());

        //Verificar que la cantidad de usuarios aumente
        User user2= new User();
        library.addUser(user2);
        assertEquals(2, library.getQuantityOfUsers());
    }

    @Test
    public void shouldAddUserSuccessfully() {
        User user = new User();
        Library library = new Library();
        boolean added = library.addUser(user);
        // Verificar que se agrego exitosamente un usuario
        assertTrue(added);
    }

    @Test
    void shouldNotAddNullUser(){
        User user = null;
        Library library = new Library();
        boolean added = library.addUser(user);
        // Verificar que no se agrego un usuario nulo
        assertFalse(added);
    }
}