package com.example.movie_management.controllers;

import com.example.movie_management.models.Movie;
import org.springframework.web.bind.annotation.*;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;


@RestController
@RequestMapping("/")
public class MovieController {

    StringBuilder moviesList = new StringBuilder();

    @GetMapping("")
    public String renderMoviesHomePage() {
        return """
                <html>
                <body>
                <h2>MOVIES</h2>
                <ul>
                """ +
                moviesList +
                """
                </ul>
                <p><a href='/add' >Add</a> a movie.</p>
                </body>
                </html>
                """;
    }

    @GetMapping("/add")
    public String renderAddMovieForm() {

        return """
                <html>
                <body>
                <form action='/add' method='POST'>
                <p>Enter a title and rating for the movie and submit to generate a description.</p>
                <input type='text' name='title' placeholder='Movie' />
                <input type='number' name='rating' placeholder='Rating' />
                <button type='submit'>Submit</button>
                </form>
                </body>
                </html>
                """;
    }

    @PostMapping("/add")
    public String processAddMovieForm(@RequestParam(value="title") String title, @RequestParam(value="rating") int rating) {
        Client client = new Client();

        String query = String.format("""
            Write me a movie description for the movie %s. 
            If you don't know this movie, get creative and make one up. 
            Keep the description under 30 words, and don't give me multiple choices. 
            Just one description, under 30 words.
            """, title);

        GenerateContentResponse response = client.models.generateContent("gemini-2.0-flash-001", query, null);
        String description = response.text();

        Movie newMovie = new Movie(title, rating, description);
        moviesList.append("<li>").append(newMovie).append("</li>");

        return """
                <html>
                <body>
                <h3>Movie added</h3>
                """ +
                "<p>You have successfully added " + title + " (Rating: " + rating + ")" + " to the collection.</p>" +
                """
                <p><a href='/add'>Add</a> another movie or view the <a href='/'>updated list</a> of movies.</p>
                </body>
                </html>
                """;
    }

}