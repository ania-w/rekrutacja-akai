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

    private static final String API_URI="https://akai-recruitment.herokuapp.com/book";
    private static List<Book> books=new ArrayList<>();
    private static List<Author> authors=new ArrayList<>();

    public static List<Book> getBooks(){
        var httpClient= HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(
                        URI.create(API_URI))
                .header("accept", "application/json")
                .GET()
                .build();

        try {
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
            books = new ObjectMapper().readValue(response, new TypeReference<>(){});
        } catch (Exception e){
            throw new RuntimeException(":(");
        }

        updateAuthors();

        return Collections.unmodifiableList(books);
    }

    public static List<Author> getAuthors(){
        return Collections.unmodifiableList(authors);
    }

    public static void printBestAuthors(){
        authors.stream()
                .map(Author::getRating)
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .forEach(System.out::println);
    }

    private static void updateAuthors(){

        Set<String> names=books.stream().map(Book::getAuthor).collect(Collectors.toSet());
        names.forEach(name->authors.add(new Author(name)));

        for(var book : books)
            authors.stream().filter(a->a.getName().equals(book.getAuthor())).findFirst().get().getBooks().add(book);
    }

}
