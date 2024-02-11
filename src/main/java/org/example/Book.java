package org.example;

import java.util.HashMap;
import java.util.Map;

public class Book {
    private String title;
    private String author;
    private int publishYear;
    private double price;
    private int stock;

    private Map<Long, Integer> reservations;
    private Map<Long, Integer> bought;


    public Book(String title, String author, int publishYear, double price, int stock){
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.price = price;
        this.stock = stock;

        reservations = new HashMap<>();
    }

    @Override
    public String toString(){
        return title + ":" +
                "\n  Author: " + author +
                "\n  Publish Year: " + publishYear +
                "\n  Price: " + price +
                "\n  Stock: " + stock + "\n";
    }

    public boolean reserveBook(Long userId, int count){
        if(stock<count){
            return false;
        }
        if(reservations.containsKey(userId)){
            count += reservations.get(userId);
        }
        reservations.put(userId, count);
        return true;
    }

    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}
    public String getAuthor(){return author;}
    public void setAuthor(String author){this.author = author;}
    public int getPublishYear(){return publishYear;}
    public void setPublishYear(int publishYear){ this.publishYear = publishYear;}
    public double getPrice(){return price;}
    public void setPrice(int price){this.price = price;}
    public int getStock(){return stock;}
    public void  setStock(int stock){this.stock = stock;}

    public void buy(Long userId, int count) {
        if(bought.containsKey(userId)){
            count += bought.get(userId);
        }
        bought.put(userId, count);
    }

    public boolean isReserved(Long userId) {
        return reservations.containsKey(userId);
    }

    public void buyReservations(Long userId) {
        int count = reservations.get(userId);
        buy(userId, count);
    }
}
