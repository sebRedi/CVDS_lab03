package edu.eci.cvds.tdd.library.loan;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.user.User;
import java.time.LocalDateTime;

public class LoanTest {

    @Test
    public void shouldSetAndKnowHisOwnParameters(){
        Book loanBook = new Book("Las aventuras de Sherlock Holmes", "Arthur Conan Doyle", "isbnX");
        User loanUser = new User();
        LocalDateTime loanDate = LocalDateTime.of(2025, 2, 9, 12, 0, 0);
        LoanStatus loanStatus = LoanStatus.ACTIVE;
        LocalDateTime loanEndDate = LocalDateTime.of(2025, 3, 9, 12, 0, 0);
        Loan loan = new Loan();

        loan.setBook(loanBook);
        loan.setUser(loanUser);
        loan.setLoanDate(loanDate);
        loan.setStatus(loanStatus);
        loan.setReturnDate(loanEndDate);

        assertEquals(loanBook, loan.getBook());
        assertEquals(loanUser, loan.getUser());
        assertEquals(loanDate, loan.getLoanDate());
        assertEquals(loanStatus, loan.getStatus());
        assertEquals(loanEndDate, loan.getReturnDate());
    }

}