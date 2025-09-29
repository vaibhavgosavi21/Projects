package Library_management;

import java.time.LocalDate;

public class IssuedBook {
    private int bookId;
    private LocalDate issueDate;
    private LocalDate dueDate;

    // Constructor
    public IssuedBook(int bookId, LocalDate issueDate, LocalDate dueDate) {
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    // Getters
    public int getBookId() { return bookId; }
    public LocalDate getIssueDate() { return issueDate; }
    public LocalDate getDueDate() { return dueDate; }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Issued: " + issueDate + ", Due: " + dueDate;
    }
}