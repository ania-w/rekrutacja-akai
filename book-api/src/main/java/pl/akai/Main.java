package pl.akai;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        BookService bookService=new BookService("https://akai-recruitment.herokuapp.com/book");

        bookService.printBooks();

        System.out.println("\n");

        bookService.printBestAuthors();
    }


}
