package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Library responsible for manage the loans and the users.
 */
public class Library {

    private final List<User> users;
    private final Map<Book, Integer> books;
    private final List<Loan> loans;

    public Library() {
        users = new ArrayList<>();
        books = new HashMap<>();
        loans = new ArrayList<>();
    }

    /**
     * Adds a new {@link edu.eci.cvds.tdd.library.book.Book} into the system, the book is store in a Map that contains
     * the {@link edu.eci.cvds.tdd.library.book.Book} and the amount of books available, if the book already exist the
     * amount should increase by 1 and if the book is new the amount should be 1, this method returns true if the
     * operation is successful false otherwise.
     *
     * @param book The book to store in the map.
     *
     * @return true if the book was stored false otherwise.
     */
    public boolean addBook(Book book) {
        boolean flag = false;
        for(Book b : books.keySet()){
            if(b.equals(book)){
                books.put(b, books.get(b)+1);
                flag = true;
                break;
            }
        }
        if(!flag){
            books.put(book, 1);
        }
        return true;
    }

    /**
     * This method creates a new loan with for the User identify by the userId and the book identify by the isbn,
     * the loan should be store in the list of loans, to successfully create a loan is required to validate that the
     * book is available, that the user exist and the same user could not have a loan for the same book
     * {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE}, once these requirements are meet the amount of books is
     * decreased and the loan should be created with {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE} status and
     * the loan date should be the current date.
     *
     * @param userId id of the user.
     * @param isbn book identification.
     *
     * @return The new created loan.
     */
    public Loan loanABook(String userId, String isbn) {
        Loan actualLoan = new Loan();
        boolean flag1 = false;
        boolean flag2 = false;
        Book bookToCheck = null;
        User userToCheck = null;
        for (Book book : books.keySet()) {
            if(book.getIsbn().equals(isbn)){
                if(getAvailableQuantityOfBooks(book) > 0){
                    flag1 = true;
                    bookToCheck = book;
                    break;
                }
            }
        }
        for (User u : users) {
            if(u.getId().equals(userId)){
                flag2 = true;
                userToCheck = u;
                break;
            }
        }
        if(flag1 && flag2){
            actualLoan.setStatus(LoanStatus.ACTIVE);
            actualLoan.setBook(bookToCheck);
            actualLoan.setUser(userToCheck);
            books.put(bookToCheck, books.get(bookToCheck) - 1);
        }
        else{
            actualLoan.setStatus(LoanStatus.RETURNED);
        }
        return actualLoan;
    }

    /**
     * This method return a loan, meaning that the amount of books should be increased by 1, the status of the Loan
     * in the loan list should be {@link edu.eci.cvds.tdd.library.loan.LoanStatus#RETURNED} and the loan return
     * date should be the current date, validate that the loan exist.
     *
     * @param loan loan to return.
     *
     * @return the loan with the RETURNED status.
     */
    public Loan returnLoan(Loan loan) {
        if (loan == null || loan.getStatus() != LoanStatus.ACTIVE) return null;

        // Marcar el préstamo como devuelto
        loan.setStatus(LoanStatus.RETURNED);
        loan.setReturnDate(LocalDate.now().atStartOfDay());

        // Aumentar la cantidad disponible del libro
        Book book = loan.getBook();
        books.put(book, books.getOrDefault(book, 0) + 1);

        return loan;
    }

    public boolean addUser(User user) {
        if (user == null) return false;
        return users.add(user);
    }

    /**
     * TDD: Obtain the quantity of books in the library
     * @return library's size
     */
    public int getQuantityOfBooks(){
        return books.size();
    }

    /**
     * TDD: Obtain the available quantity of a book
     * @param book Book to search
     * @return book's availability
     */
    public int getAvailableQuantityOfBooks(Book book){
        return books.get(book);
    }

    public int getQuantityOfUsers(){    return users.size(); }


}
