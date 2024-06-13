package features.loans.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue
    private int isbn;
    private String name;
    private LocalDate loanDate;
    private LocalDate returnDate;

    public Loan(int isbn, String name,LocalDate loanDate,LocalDate returnDate) {
        this.isbn = isbn;
        this.name = name;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public Loan() { }

    public int getIsbn() {
        return isbn;
    }
    public String getName() {
        return name;
    }
    public LocalDate getLoanDate() {
        return loanDate;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }
}
