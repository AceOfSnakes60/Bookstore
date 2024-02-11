package org.example;

/*
 - Stwórz klasę Ksiazka, która będzie reprezentować informacje o książkach w księgarni.
  Książka powinna mieć co najmniej takie dane jak tytuł, autor, rok wydania, cena, ilość dostępnych egzemplarzy itp.

 - Zaimplementuj klasę Kategoria reprezentującą kategorie książek (np. powieść, fantastyka, naukowa).
  Kategoria powinna zawierać informacje o nazwie i ewentualnie dodatkowych parametrach.

 - Utwórz klasę Ksiegarnia, która będzie zarządzała książkami.
  Ksiegarnia powinna przechowywać listę dostępnych książek, kategorie oraz informacje o klientach.

 - Dodaj funkcjonalności do dodawania, usuwania i edytowania książek w systemie.

 - Stwórz funkcję, która umożliwia dodawanie nowych kategorii do systemu.

 - Zaimplementuj mechanizm rezerwacji i sprzedaży książek. Klient powinien mieć unikalny identyfikator,
  a w przypadku sprzedaży powinien zostać zaktualizowany stan dostępności danej książki.

 - Dodaj funkcję generowania raportu, który zawiera informacje o dostępnych książkach,
  ilości egzemplarzy, sprzedanych egzemplarzach itp.

 - Stwórz prosty interfejs użytkownika (konsolowy lub okienkowy) umożliwiający obsługę systemu,
  takie jak dodawanie książek, rezerwacje, sprzedaż itp.

 - Zadbaj o obsługę błędów i walidację danych, aby system działał niezawodnie.

(Opcjonalnie) Rozważ implementację prostego systemu kont użytkowników,
 aby różne osoby miały różne poziomy dostępu do funkcji systemu.
 */

import org.example.UI.BookstoreUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App
{


    public static void main( String[] args )
    {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("lotr", "Tolkien", 1962, 12.22, 30));

        Bookstore bookstore = new Bookstore(bookList);
        BookstoreUI ui = new BookstoreUI(bookstore);

        ui.run();

    }

    public static void testUI(){
        BookstoreUI ui = new BookstoreUI(new Bookstore());
        ui.register();
    }

}

