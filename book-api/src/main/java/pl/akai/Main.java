package pl.akai;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Book> books=BookService.getBooks();
        System.out.println(books);

        List<Author> authors=BookService.getAuthors();
        System.out.println(authors);

        BookService.printBestAuthors();

    }


}
