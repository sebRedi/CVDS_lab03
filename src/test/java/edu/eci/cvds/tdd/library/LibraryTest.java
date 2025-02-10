package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.user.User;
import edu.eci.cvds.tdd.library.loan.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    // AddBook

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

    // LoanABook

    @Test
    public void shouldDecreaseTheAmountOfABookWhenLoan(){
        Library library = new Library();
        Book testBook = new Book("100 años de soledad", "Gabriel Garcia", "1000");
        library.addBook(testBook);
        User user = new User();
        user.setName("Pepito Perez");
        user.setId("123456");
        library.addUser(user);

        Loan testLoan = library.loanABook("123456", "1000");

        assertEquals(0, library.getAvailableQuantityOfBooks(testBook));
    }

    @Test
    public void shouldLoanSuccessfully(){
        Library library = new Library();
        Book testBook = new Book("100 años de soledad", "Gabriel Garcia", "1000");
        library.addBook(testBook);
        User user = new User();
        user.setName("Pepito Perez");
        user.setId("123456");
        library.addUser(user);

        Loan testLoan = library.loanABook("123456", "1000");

        assertEquals(LoanStatus.ACTIVE, testLoan.getStatus());
        assertEquals(0, library.getAvailableQuantityOfBooks(testBook));
    }

    @Test
    public void shouldVerifyIfTheLoanUserExist(){
        Library library = new Library();
        Book testBook = new Book("100 años de soledad", "Gabriel Garcia", "1000");
        library.addBook(testBook);

        Loan testLoan = library.loanABook("000000", "1000");

        assertEquals(LoanStatus.RETURNED, testLoan.getStatus());
        assertEquals(1, library.getAvailableQuantityOfBooks(testBook));
    }

    @Test
    public void shouldNotLoanANonExistingBook(){
        Library library = new Library();
        Book testBook = new Book("100 años de soledad", "Gabriel Garcia", "1000");
        library.addBook(testBook);
        User user = new User();
        user.setName("Pepito Perez");
        user.setId("123456");
        library.addUser(user);

        Loan testLoan = library.loanABook("123456", "1001");

        assertEquals(LoanStatus.RETURNED, testLoan.getStatus());
        assertEquals(1, library.getAvailableQuantityOfBooks(testBook));
    }

    @Test
    public void shouldNotLoanAnOutOfStockBook(){
        Library library = new Library();
        Book testBook = new Book("100 años de soledad", "Gabriel Garcia", "1000");
        library.addBook(testBook);
        User user = new User();
        user.setName("Pepito Perez");
        user.setId("123456");
        library.addUser(user);

        Loan loan = library.loanABook("123456", "1000");
        Loan testLoan = library.loanABook("123456", "1000");

        assertEquals(LoanStatus.RETURNED, testLoan.getStatus());
        assertEquals(0, library.getAvailableQuantityOfBooks(testBook));
    }

}