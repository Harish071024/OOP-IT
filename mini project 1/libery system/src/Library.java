import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Library {
    private HashMap<String, Book> inventory;
    private HashMap<String, Member> members;

    public Library() {
        inventory = new HashMap<>();
        members = new HashMap<>();
    }

    public void addBook(Book book) {
        inventory.put(book.getId(), book);
        logOperation("Added Book: " + book.getId());
    }

    public void addMember(Member member) {
        members.put(member.getMemberId(), member);
        logOperation("Added Member: " + member.getMemberId());
    }

    public void issueBook(String bookId, String memberId) throws BookNotAvailableException {
        Book book = inventory.get(bookId);
        Member member = members.get(memberId);

        if (book == null || book.isIssued())
            throw new BookNotAvailableException("Book is not available or does not exist.");

        if (member == null)
            throw new BookNotAvailableException("Member not found.");

        book.setIssued(true);
        member.borrowBook(bookId);

        logOperation("Issued Book: " + bookId + " to Member: " + memberId);
    }

    public void returnBook(String bookId, String memberId, int daysLate) throws InvalidReturnException {
        Book book = inventory.get(bookId);
        Member member = members.get(memberId);

        if (book == null || member == null || !member.getBorrowedBookIds().contains(bookId))
            throw new InvalidReturnException("Invalid return attempt.");

        book.setIssued(false);
        member.returnBook(bookId);

        int fine = daysLate * 2;

        logOperation("Returned Book: " + bookId + " from Member: " + memberId + " | Fine: ₹" + fine);

        System.out.println("Book returned successfully. Fine: ₹" + fine);
    }

    public void showInventory() {
        System.out.println("\n----- Library Inventory -----");
        for (Book b : inventory.values()) {
            System.out.println(b);
        }
        System.out.println("-----------------------------\n");
    }

    public void logOperation(String message) {
        try (FileWriter fw = new FileWriter("library_log.txt", true)) {
            fw.write(message + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to log file.");
        }
    }
}
