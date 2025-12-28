package com.movies.api.controller;

import com.movies.api.model.Movie;
import com.movies.api.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    /**
     * Read - Get all products
     * @return - An Iterable object of products full filled
     */
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        return movieService.updateMovie(id, movieDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}

/*
* @GetMapping
GET
Pour la lecture de données.

@PostMapping
POST
Pour l’envoi de données. Cela sera utilisé par exemple pour créer un nouvel élément.

@PatchMapping
PATCH
Pour la mise à jour partielle de l’élément envoyé.

@PutMapping
PUT
Pour le remplacement complet de l’élément envoyé.

@DeleteMapping
DELETE
Pour la suppression de l’élément envoyé.

@RequestMapping
Intègre tous les types HTTP. Le type souhaité est indiqué comme attribut de l’annotation. Exemple :
@RequestMapping(method = RequestMethod.GET)
*
* */