package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;

import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;

import org.junit.jupiter.api.*;

import java.time.LocalDate;

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
        assertEquals(1,library.getAvailableQuantityOfBooks(testBook));

        library.addBook(testBook);
        // Ver que la cantidad de libros disponibles de un libro específico haya aumentado
        assertEquals(2,library.getAvailableQuantityOfBooks(testBook));
        }

    @Test
    public void shouldAddBookToLibrary(){
        Book testBook = new Book("100 años de soledad", "Gabriel Garcia", "1000");
        Library library = new Library();
        library.addBook(testBook);
        assertTrue(1 >= library.getAvailableQuantityOfBooks(testBook));
    }

    @Test
    public void shouldReturnLoanSuccessfuly(){
        Library library = new Library();
        User user = new User();
        Book book = new Book("El Quijote", "Miguel Saavedra", "1001");

        library.addUser(user);
        library.addBook(book);

        // Simulamos un préstamo activo
        Loan loan = new Loan();
        library.loanABook(user.getId(), book.getIsbn());
        Loan returnedLoan = library.returnLoan(loan);

        assertEquals(LoanStatus.RETURNED, returnedLoan.getStatus());

        assertEquals(1, library.getAvailableQuantityOfBooks(book));
    }

    @Test
    public void shouldIncreaseBookQuantityWhenLoanIsReturned() {
        Book book = new Book("El Quijote", "Miguel Saavedra", "1001");
        Library library = new Library();
        Loan loan = new Loan();

        int initialQuantity = library.getAvailableQuantityOfBooks(book); // Cantidad antes de la devolución

        Loan returnedLoan = library.returnLoan(loan);

        int updatedQuantity = library.getAvailableQuantityOfBooks(book); // Cantidad después de la devolución

        assertEquals(initialQuantity + 1, updatedQuantity);
        assertEquals(LoanStatus.RETURNED, returnedLoan.getStatus());

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