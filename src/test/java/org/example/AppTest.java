package org.example;


import org.example.UI.BookstoreUI;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AppTest
{
    @Test
    public void testAddition(){

        List<Book> bookList = new ArrayList<Book>();
        Bookstore bookstore = new Bookstore(bookList);
        System.out.println("1: \n" + bookstore.generateReport());
        bookstore.addBook(new Book("Test", "Testicularius", 1995, 4.99,99));
        bookstore.addBook(new Book("Lord of the Rings", "J.R.R. Tolkien", 1961, 10.99, 55));
        System.out.println("2: \n" + bookstore.generateReport());

        bookstore.removeBook("Test");

        System.out.println("3: \n" + bookstore.generateReport());
    }




}
