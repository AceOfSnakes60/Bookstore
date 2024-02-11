package org.example.UI;

import org.example.Book;
import org.example.Bookstore;
import org.example.UI.BookstoreUIInterface;
import org.example.User;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class BookstoreUI implements BookstoreUIInterface {

    private Scanner scanner;
    private Bookstore bookstore;

    private User user = null;

    public BookstoreUI(Bookstore bookstore){
        this.bookstore = bookstore;
        scanner = new Scanner(System.in);
    }

    public void run(){
        while(true) {
            if (user == null) {

                System.out.println(
                        """
                                1) login\s
                                2) register
                                3) Add Book
                                4) Remove Book
                                5) Update Book6) Print Report7) Quit"""
                        );
                int input1 = scanner.nextInt();
                switch (input1){
                    case 1:
                        login();
                        break;
                    case 2:
                        register();
                        break;
                    case 3:
                        addBook();
                        break;
                    case 4:
                        removeBook();
                        break;
                    case 5:
                        updateBook();
                        break;
                    case 6:
                        bookstore.generateReport();
                        break;
                    case 7:
                        return;
                }
            }
            System.out.println("Welcome " + user.getUsername());
            System.out.println("""

                     1) Add Book
                    2) Remove Book
                    3) Update Book
                    4) Reserve book
                    5) Buy Book
                    6) Buy Reserved
                    7) Print Report
                    8) Log out
                    9) Quit
                    """);
            int input2 = scanner.nextInt();
            switch(input2){
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    reserveBook();
                    break;
                case 5:
                    buyBook();
                    break;
                case 6:
                    buyReserved();
                    break;
                case 7:
                    bookstore.generateReport();
                    break;
                case 8:
                    user = null;
                    break;
                case 9:
                    return;
            }
        }
    }

    public void register(){
        System.out.println("Type unique username:");
        String username;
        while(true) {
            String input1 = scanner.nextLine();
            if(input1.equals("q")){
                return;
            }
            if(!bookstore.isUsernameValid(input1)){
                System.out.println("Username already taken.");
                continue;
            }
            System.out.println("username pass");
            username = input1;
            break;
        }

        System.out.println("Type unique e-mail:");
        String email;
        while(true) {
            String input2 = scanner.nextLine();
            if(input2.equals("q")){
                return;
            }
            if(!bookstore.isEmailValid(input2)){
                System.out.println("Email already taken.");
                continue;
            }
            email = input2;
            break;
        }

        System.out.println("Type your password:");
        String password;
        while (true) {

            String input3 = scanner.nextLine();
            if(input3.equals("q")){
                return;
            }
            password = input3;
            break;
        }
        System.out.println("Repeat password:");
        while (true) {

            String input3 = scanner.nextLine();
            if(input3.equals("q")){
                return;
            }
            if(input3.equals(password)){
                System.out.println("Passwords dont match.");
                continue;
            }
            break;
        }

        User newUser = new User(username, email, password);
        System.out.println("Is this user details correct:\n" + newUser.toString() + "y/n");
        String input4 = scanner.nextLine();
        if(input4.equals("y")){
            bookstore.addUser(newUser);
            System.out.println("User created.");
        }
    }
    public void login(){
        System.out.println("Type your username");
        String input1 = scanner.nextLine();
        Optional<User> found = bookstore.findUser(input1);
        if(found.isEmpty()) {
            System.out.println("Username not found!");
            return;
        }
        System.out.println("Type your password");
        String input2 = scanner.nextLine();
        if(!found.get().checkPassword(input2)){
            System.out.println("Wrong password");
            return;
        }
        user = found.get();


    }

    public void addBook(){
        System.out.println("Adding a book. Type c to cancel.");

        System.out.println("Title:");
        String title = scanner.nextLine();
        System.out.println("Author:");
        String author = scanner.nextLine();
        System.out.println("Publishing date:");
        int publishingDate = scanner.nextInt();
        System.out.println("Price:");
        double price = scanner.nextDouble();
        System.out.println("Stock:");
        int stock = scanner.nextInt();

        Book newBook = new Book(title, author, publishingDate, price, stock);
        System.out.println(newBook.toString() + "\n\n Do you wish to add this book?\n y - add book  n - change details q - cancel process");
        char command = scanner.nextLine().charAt(0);
        if(command == 'y'){
            bookstore.addBook(newBook);
        } else if (command == 'n') {

        } else if (command == 'q') {

        } else {
            System.out.println("Wrong command");
        }

    }

    public void removeBook(){
        System.out.println("Type title of the book to remove");
        String title = scanner.nextLine();
        Optional<Book> found = bookstore.findBook(title);
        if(found.isEmpty()) {
            System.out.println("Book not found.");
            return;
        }
        System.out.println(found.get().toString());
        System.out.println("Is this the book you wish to remove?\ny/n");

        while(true) {
            String answer = scanner.nextLine();
            if(answer.equals("q")){
                return;
            }
            if (answer.equals("y")) {
                bookstore.removeBook(found.get());
                return;
            }
            if (answer.equals("n")) {
                return;
            }
        }
    }
    public void updateBook(){
        System.out.println("Give title of the book to edit");
        System.out.println("Book not found");
        System.out.println("Is this book correct? y/n");

    }

    public void reserveBook(){
        System.out.println("Write the name of the book you wish to reserve:");
        String input1 = scanner.nextLine();
        Optional<Book> found = bookstore.findBook(input1);
        if(found.isEmpty()) {
            System.out.println("Book not found");
            return;
        }
        System.out.println("Is this book correct? y/n");
        String input2 = scanner.nextLine();
        if(input2.equals("n")) {
            return;
        }
        System.out.println("How many copies? \n Copies available: " + found.get().getStock());
        int input3 = scanner.nextInt();
        if(input3 > found.get().getStock()){
            System.out.println("Too many copies requested");
            return;
        }

        if(found.get().reserveBook(user.getUserId(), input3)){
            user.reserveBook(found.get().getTitle(), input3);
            System.out.println("Book successfully reserved");
        }

    }

    public void buyBook(){
        System.out.println("Type title you wish to buy:");
        String input1 = scanner.nextLine();
        Optional<Book> found = bookstore.findBook(input1);
        if(found.isEmpty()) {
            System.out.println("Book not found");
        }
        System.out.println("Is this the book:");
        String input2 = scanner.nextLine();
        if(input2.equals("n")){
            return;
        }
        System.out.println("How many copies to buy?");
        int input3 = scanner.nextInt();
        if(found.get().getStock()<input3) {
            System.out.println("Not enough copies in store");
         return;
        }

        bookstore.buyBook(found.get(), user.getUserId(), input3);

        System.out.println("Book successfully bought");
    }
    public void buyReserved(){
        List<Book> reservedBooks = bookstore.getReserved(user.getUserId());
        if(reservedBooks.isEmpty()){
            System.out.println("No reservations found.");
            return;
        }
        System.out.println("Reserved Books:");
        reservedBooks.forEach(b-> System.out.println(b.getTitle()+"\n"));

        System.out.println("Do you wish to buy reservations? y/n");
        while(true) {
            String input1 = scanner.nextLine();
            if (input1.equals("n")) {
                return;
            }
            if (input1.equals("y")) {
                bookstore.buyReservationByUser(user.getUserId());
                System.out.println("Books bought.");
                return;
            }
            System.out.println("Wrong input");
        }

    }
}
