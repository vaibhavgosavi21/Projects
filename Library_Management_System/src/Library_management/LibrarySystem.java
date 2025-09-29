package Library_management;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class LibrarySystem {
    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, Member> members = new HashMap<>();
    private List<Transaction> transactions = new ArrayList<>();
    private static int nextBookId = 1;
    private static int nextMemberId = 1;

    private Scanner scanner = new Scanner(System.in);

    // Safe integer input method to avoid Scanner issues
    private int getIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid integer: ");
            }
        }
    }

    // Admin login
    public boolean authenticate() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        if ("admin".equals(username) && "password".equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid credentials!");
            return false;
        }
    }

    // Main menu after login
    public void showMenu() {
        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Book Management");
            System.out.println("2. Member Management");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Books");
            System.out.println("6. View All Books");
            System.out.println("7. View All Members");
            System.out.println("8. View Transaction History");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            int choice = getIntInput();

            switch (choice) {
                case 1: bookManagement(); break;
                case 2: memberManagement(); break;
                case 3: issueBook(); break;
                case 4: returnBook(); break;
                case 5: searchBooks(); break;
                case 6: viewAllBooks(); break;
                case 7: viewAllMembers(); break;
                case 8: viewTransactions(); break;
                case 0: System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid option!");
            }
        }
    }

    // 1. Book Management (Admin only)
    private void bookManagement() {
        while (true) {
            System.out.println("\n--- Book Management ---");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Back");
            System.out.print("Choose: ");
            int choice = getIntInput();

            switch (choice) {
                case 1: addBook(); break;
                case 2: updateBook(); break;
                case 3: deleteBook(); break;
                case 4: return;
                default: System.out.println("Invalid!");
            }
        }
    }

    private void addBook() {
        System.out.print("Title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty!");
            return;
        }
        System.out.print("Author: ");
        String author = scanner.nextLine().trim();
        if (author.isEmpty()) {
            System.out.println("Author cannot be empty!");
            return;
        }
        System.out.print("Category (e.g., Fiction/Non-fiction/Science): ");
        String category = scanner.nextLine().trim();
        if (category.isEmpty()) {
            System.out.println("Category cannot be empty!");
            return;
        }
        System.out.print("Quantity: ");
        int quantity = getIntInput();
        if (quantity < 0) {
            System.out.println("Quantity cannot be negative!");
            return;
        }
        int id = nextBookId++;
        Book book = new Book(id, title, author, category, quantity);
        books.put(id, book);
        System.out.println("Book added with ID: " + id);
    }

    private void updateBook() {
        if (books.isEmpty()) {
            System.out.println("No books available!");
            return;
        }
        viewAllBooks();
        System.out.print("Enter Book ID to update: ");
        int id = getIntInput();
        Book book = books.get(id);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }
        System.out.print("New Title (or press Enter to skip): ");
        String title = scanner.nextLine().trim();
        if (!title.isEmpty()) book.setTitle(title);
        System.out.print("New Author (or press Enter to skip): ");
        String author = scanner.nextLine().trim();
        if (!author.isEmpty()) book.setAuthor(author);
        System.out.print("New Category (or press Enter to skip): ");
        String category = scanner.nextLine().trim();
        if (!category.isEmpty()) book.setCategory(category);
        System.out.print("New Quantity (or press Enter to skip): ");
        String qtyStr = scanner.nextLine().trim();
        if (!qtyStr.isEmpty()) {
            try {
                int qty = Integer.parseInt(qtyStr);
                if (qty >= 0) {
                    book.setQuantity(qty);
                } else {
                    System.out.println("Quantity cannot be negative! Skipping.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity! Skipping.");
            }
        }
        System.out.println("Book updated!");
    }

    private void deleteBook() {
        if (books.isEmpty()) {
            System.out.println("No books available!");
            return;
        }
        viewAllBooks();
        System.out.print("Enter Book ID to delete: ");
        int id = getIntInput();
        if (books.remove(id) != null) {
            System.out.println("Book deleted!");
        } else {
            System.out.println("Book not found!");
        }
    }

    private void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in inventory!");
            return;
        }
        System.out.println("\n--- All Books ---");
        books.values().forEach(System.out::println);
    }

    // 2. Member Management
    private void memberManagement() {
        while (true) {
            System.out.println("\n--- Member Management ---");
            System.out.println("1. Add Member");
            System.out.println("2. Update Member");
            System.out.println("3. View Members");
            System.out.println("4. Back");
            System.out.print("Choose: ");
            int choice = getIntInput();

            switch (choice) {
                case 1: addMember(); break;
                case 2: updateMember(); break;
                case 3: viewAllMembers(); break;
                case 4: return;
                default: System.out.println("Invalid!");
            }
        }
    }

    private void addMember() {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }
        System.out.print("Mobile Number: ");
        String mobile = scanner.nextLine().trim();
        if (mobile.isEmpty()) {
            System.out.println("Mobile cannot be empty!");
            return;
        }
        LocalDate membershipDate = LocalDate.now();  // Default to today
        int id = nextMemberId++;
        Member member = new Member(id, name, mobile, membershipDate);
        members.put(id, member);
        System.out.println("Member added with ID: " + id + ". Membership Date: " + membershipDate);
    }

    private void updateMember() {
        if (members.isEmpty()) {
            System.out.println("No members available!");
            return;
        }
        viewAllMembers();
        System.out.print("Enter Member ID to update: ");
        int id = getIntInput();
        Member member = members.get(id);
        if (member == null) {
            System.out.println("Member not found!");
            return;
        }
        System.out.print("New Name (or press Enter to skip): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) member.setName(name);
        System.out.print("New Mobile (or press Enter to skip): ");
        String mobile = scanner.nextLine().trim();
        if (!mobile.isEmpty()) member.setMobileNumber(mobile);
        System.out.println("Member updated!");
    }

    private void viewAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered!");
            return;
        }
        System.out.println("\n--- All Members ---");
        members.values().forEach(System.out::println);
    }

    // 3. Issue Book
    private void issueBook() {
        if (members.isEmpty()) {
            System.out.println("No members available!");
            return;
        }
        viewAllMembers();
        System.out.print("Enter Member ID: ");
        int memberId = getIntInput();
        Member member = members.get(memberId);
        if (member == null) {
            System.out.println("Member not found!");
            return;
        }
        if (!member.canBorrow()) {
            System.out.println("Member has reached max 3 books limit! Current borrowed: " + member.getBorrowedCount());
            return;
        }

        if (books.isEmpty()) {
            System.out.println("No books available!");
            return;
        }
        viewAllBooks();
        System.out.print("Enter Book ID to issue: ");
        int bookId = getIntInput();
        Book book = books.get(bookId);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }
        if (book.getQuantity() <= 0) {
            System.out.println("Book not available! Quantity: " + book.getQuantity());
            return;
        }

        LocalDate issueDate = LocalDate.now();
        LocalDate dueDate = issueDate.plusDays(7);
        IssuedBook issuedBook = new IssuedBook(bookId, issueDate, dueDate);
        member.addIssuedBook(issuedBook);
        book.setQuantity(book.getQuantity() - 1);

        // Log transaction
        transactions.add(new Transaction("ISSUE", issueDate, bookId, memberId));
        System.out.println("Book issued successfully to " + member.getName() + "!");
        System.out.println("Due date: " + dueDate);
    }

    // 4. Return Book
    private void returnBook() {
        if (members.isEmpty()) {
            System.out.println("No members available!");
            return;
        }
        viewAllMembers();
        System.out.print("Enter Member ID: ");
        int memberId = getIntInput();
        Member member = members.get(memberId);
        if (member == null) {
            System.out.println("Member not found!");
            return;
        }
        if (member.getIssuedBooks().isEmpty()) {
            System.out.println("No books borrowed by this member!");
            return;
        }

        System.out.println("\n--- Member's Borrowed Books ---");
        for (IssuedBook ib : member.getIssuedBooks()) {
            System.out.println(ib);
        }
        System.out.print("Enter Book ID to return: ");
        int bookId = getIntInput();

        // Find the IssuedBook BEFORE removing to get due date
        IssuedBook toReturn = null;
        for (IssuedBook ib : member.getIssuedBooks()) {
            if (ib.getBookId() == bookId) {
                toReturn = ib;
                break;
            }
        }
        if (toReturn == null) {
            System.out.println("Book not borrowed by this member!");
            return;
        }

        // Now remove it
        boolean removed = member.removeIssuedBook(bookId);
        if (!removed) {
            System.out.println("Failed to return book!");
            return;
        }

        Book book = books.get(bookId);
        if (book == null) {
            System.out.println("Book not found in inventory!");
            return;
        }
        book.setQuantity(book.getQuantity() + 1);

        LocalDate dueDate = toReturn.getDueDate();
        LocalDate returnDate = LocalDate.now();
        long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
        double fine = (daysLate > 0) ? daysLate * 10 : 0;

        // Log transaction
        transactions.add(new Transaction("RETURN", returnDate, bookId, memberId));

        System.out.println("Book returned successfully!");
        System.out.println("Return Summary - Return Date: " + returnDate + ", Due Date: " + dueDate);
        if (daysLate > 0) {
            System.out.println("Late by " + daysLate + " days. Fine: ₹" + fine);
        } else {
            System.out.println("Returned on time. No fine.");
        }
    }

    // 5. Search Books
    private void searchBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available!");
            return;
        }
        System.out.println("\n--- Search Books ---");
        System.out.println("1. Search by Title");
        System.out.println("2. Search by Author");
        System.out.println("3. Search by Category");
        System.out.print("Choose search type: ");
        int searchType = getIntInput();
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        if (keyword.isEmpty()) {
            System.out.println("Keyword cannot be empty!");
            return;
        }

        List<Book> results = new ArrayList<>();
        switch (searchType) {
            case 1:
                results = books.values().stream()
                        .filter(b -> b.getTitle().toLowerCase().contains(keyword))
                        .collect(Collectors.toList());
                break;
            case 2:
                results = books.values().stream()
                        .filter(b -> b.getAuthor().toLowerCase().contains(keyword))
                        .collect(Collectors.toList());
                break;
            case 3:
                results = books.values().stream()
                        .filter(b -> b.getCategory().toLowerCase().contains(keyword))
                        .collect(Collectors.toList());
                break;
            default:
                System.out.println("Invalid search type!");
                return;
        }

        if (results.isEmpty()) {
            System.out.println("No books found matching '" + keyword + "'!");
        } else {
            System.out.println("\n--- Search Results (" + results.size() + ") ---");
            results.forEach(System.out::println);
        }
    }

    // 6. View Transaction History
    private void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions recorded yet!");
            return;
        }
        // Sort by date (ascending)
        List<Transaction> sortedTransactions = transactions.stream()
                .sorted(Comparator.comparing(Transaction::getTransactionDate))
                .collect(Collectors.toList());
        System.out.println("\n--- Transaction History ---");
        sortedTransactions.forEach(System.out::println);
    }
}