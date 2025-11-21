import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Show Inventory");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Book ID: ");
                        String id = sc.nextLine();
                        System.out.print("Enter Title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter Author: ");
                        String author = sc.nextLine();

                        library.addBook(new Book(id, title, author));
                        break;

                    case 2:
                        System.out.print("Enter Member ID: ");
                        String mId = sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        library.addMember(new Member(mId, name));
                        break;

                    case 3:
                        System.out.print("Enter Book ID: ");
                        String bId = sc.nextLine();
                        System.out.print("Enter Member ID: ");
                        String memId = sc.nextLine();

                        library.issueBook(bId, memId);
                        System.out.println("Book issued successfully!");
                        break;

                    case 4:
                        System.out.print("Enter Book ID: ");
                        String rbId = sc.nextLine();
                        System.out.print("Enter Member ID: ");
                        String rMemId = sc.nextLine();
                        System.out.print("Enter days late: ");
                        int daysLate = sc.nextInt();

                        library.returnBook(rbId, rMemId, daysLate);
                        break;

                    case 5:
                        library.showInventory();
                        break;

                    case 6:
                        System.out.println("Exiting... Goodbye!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }
}
