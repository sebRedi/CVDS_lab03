package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.BookTest;
import edu.eci.cvds.tdd.library.loan.LoanTest;
import edu.eci.cvds.tdd.library.loan.LoanStatusTest;
import edu.eci.cvds.tdd.library.user.UserTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Library responsible for manage the loans and the users.
 */
public class LibraryTest {

    private final List<UserTest> users;
    private final Map<BookTest, Integer> books;
    private final List<LoanTest> loans;

    public LibraryTest() {
        users = new ArrayList<>();
        books = new HashMap<>();
        loans = new ArrayList<>();
    }

    /**
     * Adds a new {@link BookTest} into the system, the book is store in a Map that contains
     * the {@link BookTest} and the amount of books available, if the book already exist the
     * amount should increase by 1 and if the book is new the amount should be 1, this method returns true if the
     * operation is successful false otherwise.
     *
     * @param book The book to store in the map.
     *
     * @return true if the book was stored false otherwise.
     */
    public boolean addBook(BookTest book) {
        //TODO Implement the logic to add a new book into the map.
        return false;
    }

    /**
     * This method creates a new loan with for the User identify by the userId and the book identify by the isbn,
     * the loan should be store in the list of loans, to successfully create a loan is required to validate that the
     * book is available, that the user exist and the same user could not have a loan for the same book
     * {@link LoanStatusTest#ACTIVE}, once these requirements are meet the amount of books is
     * decreased and the loan should be created with {@link LoanStatusTest#ACTIVE} status and
     * the loan date should be the current date.
     *
     * @param userId id of the user.
     * @param isbn book identification.
     *
     * @return The new created loan.
     */
    public LoanTest loanABook(String userId, String isbn) {
        //TODO Implement the login of loan a book to a user based on the UserId and the isbn.
        return null;
    }

    /**
     * This method return a loan, meaning that the amount of books should be increased by 1, the status of the Loan
     * in the loan list should be {@link LoanStatusTest#RETURNED} and the loan return
     * date should be the current date, validate that the loan exist.
     *
     * @param loan loan to return.
     *
     * @return the loan with the RETURNED status.
     */
    public LoanTest returnLoan(LoanTest loan) {
        //TODO Implement the login of loan a book to a user based on the UserId and the isbn.
        return null;
    }

    public boolean addUser(UserTest user) {
        return users.add(user);
    }

}