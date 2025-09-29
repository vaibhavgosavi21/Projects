package Library_management;

import java.time.LocalDate;

public class Transaction {
    private String type;  // "ISSUE" or "RETURN"
    private LocalDate transactionDate;
    private int bookId;
    private int memberId;

    // Constructor
    public Transaction(String type, LocalDate transactionDate, int bookId, int memberId) {
        this.type = type;
        this.transactionDate = transactionDate;
        this.bookId = bookId;
        this.memberId = memberId;
    }

    // Getters
    public String getType() { return type; }
    public LocalDate getTransactionDate() { return transactionDate; }
    public int getBookId() { return bookId; }
    public int getMemberId() { return memberId; }

    @Override
    public String toString() {
        return type + " - Date: " + transactionDate + ", Book ID: " + bookId + ", Member ID: " + memberId;
    }
}
