package Task2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class RestApiClientAsync {
    public static void main(String[] args) {
        // Create an HttpClient instance
        HttpClient client = HttpClient.newHttpClient();
        
        // Create a GET request to the URL
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .build();

        // Send the GET request asynchronously
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        // Handle the response when it's completed
        response.thenAccept(r -> {
            System.out.println("Response code: " + r.statusCode());
            System.out.println("Response body: " + r.body());
        }).exceptionally(e -> {
            System.out.println("Request failed: " + e.getMessage());
            return null;
        });

        // You can wait for the asynchronous task to complete if necessary
        // response.join();
    }
}
