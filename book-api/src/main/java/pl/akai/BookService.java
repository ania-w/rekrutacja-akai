package pl.akai;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;

public class BookService {

    private final String API_URI;
    private List<Book> books;

    public BookService(String API_URI) {
        this.API_URI = API_URI;
    }

    public List<Book> getBooks(){
        var httpClient= HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(
                        URI.create(API_URI))
                .header("accept", "application/json")
                .GET()
                .build();

        books=new ArrayList<>();
        try {
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
            books = new ObjectMapper().readValue(response, new TypeReference<List<Book>>(){});
        } catch (Exception e){
            throw new RuntimeException(":(");
        }

        return Collections.unmodifiableList(books);
    }

    public void printBooks(){

        if(books==null) getBooks();

        books.forEach(System.out::println);
    }

    public void printBestAuthors(){

        if(books==null) getBooks();

        getAuthors().stream()
                .sorted(Comparator.comparing(Author::getRating).reversed())
                .limit(3)
                .map(author->new StringBuilder().append(author.getName()).append(" ").append(author.getRating()))
                .forEach(System.out::println);
    }

    public List<Author> getAuthors(){
        List<Author> authors=new ArrayList<>();
        books.stream().map(Book::getAuthor).collect(Collectors.toSet())
                .forEach(name->authors.add(new Author(name)));

        books.forEach(book->
                authors.stream().filter(a->a.getName().equals(book.getAuthor()))
                        .findFirst().get().addBook(book));


        return Collections.unmodifiableList(authors);
    }

}
