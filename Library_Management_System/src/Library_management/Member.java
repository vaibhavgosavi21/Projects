package Library_management;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Member {
    private int memberId;
    private String name;
    private String mobileNumber;
    private LocalDate membershipDate;
    private List<IssuedBook> issuedBooks;  // Tracks currently borrowed books (max 3)

    // Constructor
    public Member(int memberId, String name, String mobileNumber, LocalDate membershipDate) {
        this.memberId = memberId;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.membershipDate = membershipDate;
        this.issuedBooks = new ArrayList<>();
    }

    // Getters and Setters
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public LocalDate getMembershipDate() { return membershipDate; }
    public void setMembershipDate(LocalDate membershipDate) { this.membershipDate = membershipDate; }

    public List<IssuedBook> getIssuedBooks() { return issuedBooks; }
    public void setIssuedBooks(List<IssuedBook> issuedBooks) { this.issuedBooks = issuedBooks; }

    // Method to check if member can borrow (less than 3 books)
    public boolean canBorrow() {
        return issuedBooks.size() < 3;
    }

    // Method to add issued book
    public void addIssuedBook(IssuedBook issuedBook) {
        issuedBooks.add(issuedBook);
    }

    // Method to remove issued book by book ID
    public boolean removeIssuedBook(int bookId) {
        return issuedBooks.removeIf(ib -> ib.getBookId() == bookId);
    }

    // Get number of borrowed books
    public int getBorrowedCount() {
        return issuedBooks.size();
    }

    @Override
    public String toString() {
        return "Member ID: " + memberId + ", Name: '" + name + "', Mobile: " + mobileNumber +
               ", Membership Date: " + membershipDate + ", Borrowed Books: " + getBorrowedCount();
    }
}
