package pl.akai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Author {

    private String name;
    private Double rating;
    private final List<Book> books=new ArrayList<>();

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public void addBook(Book book) {
        books.add(book);
        updateRating();
    }

    private void updateRating() {
        rating=books.stream().mapToDouble(Book::getRating).sum()
                /books.size();
    }
}
