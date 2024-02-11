package org.example;

import java.io.BufferedWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Bookstore {
    private List<Book> books;
    private List<Category> categories;
    private List<User> users;

    public Bookstore(){
        books = new ArrayList<Book>();
        categories = new ArrayList<Category>();
        users = new ArrayList<User>();
    }
    public Bookstore(List<Book> books){

        this.books = books;
        this.users = new ArrayList<User>();
        this.categories = new ArrayList<Category>();
    }

    public Bookstore(List<Book> books, List<Category> categories){
        this.books = books;
        this.categories = categories;
    }

    public List<Book> getAllBooks(){
        return books;
    }


    public void addBook(Book newBook){
        books.add(newBook);
    }
    public void removeBook(String name){

        Optional<Book> toRemove = books.stream().filter(book -> {return book.getTitle().equals(name);}).findFirst();
        toRemove.ifPresent(book -> books.remove(book));
    }
    public void removeBook(Book book){
        books.remove(book);
    }

    public String generateReport(){
        return books.stream()
                .map(Object::toString)
                .reduce((b1,b2) -> b1 +  b2)
                .orElse("List is empty.");

    }

    public void addCategory(Category category){
        categories.add(category);
    }

    public void addUser(User user){
        user.setUserID(users.get(users.size()-1).getUserId()+1);
        users.add(user);
    }

    public boolean isUsernameValid(String username){
        return users.stream().filter(u->u.getUsername().equals(username)).findFirst().isEmpty();
    }
    public boolean isEmailValid(String email){
        return users.stream().filter(u->u.getEmail().equals(email)).findFirst().isEmpty();
    }

    public Optional<Book> findBook(String title) {
        return books.stream().filter(b->b.getTitle().equals(title)).findFirst();

    }

    public Optional<User> findUser(String input1) {
        return users.stream().filter(u->u.getUsername().equals(input1)).findFirst();
    }

    public void buyBook(Book book, Long userId, int count) {
        books.get(books.indexOf(book)).buy(userId, count);
    }

    public List<Book> getReserved(Long userId) {
        return books.stream().filter(b->b.isReserved(userId)).toList();
    }

    public void buyReservationByUser(Long userId){
        books.stream().filter(b->b.isReserved(userId)).forEach(e->e.buyReservations(userId));
    }
}
