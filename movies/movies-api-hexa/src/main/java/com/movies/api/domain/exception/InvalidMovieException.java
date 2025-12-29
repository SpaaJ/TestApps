package com.movies.api.domain.exception;

public class InvalidMovieException extends RuntimeException {
    public InvalidMovieException(String message) {
        super(message);
    }
}
