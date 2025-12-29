package com.movies.api.infrastructure.controller.mapper;

import com.movies.api.domain.model.Movie;
import com.movies.api.infrastructure.controller.dto.MovieRequest;
import com.movies.api.infrastructure.controller.dto.MovieResponse;
import org.springframework.stereotype.Component;

@Component
public class MovieDtoMapper {

    public Movie toDomain(MovieRequest request) {
        return Movie.builder()
                .imdbId(request.getImdbId())
                .title(request.getTitle())
                .originalTitle(request.getOriginalTitle())
                .releasedDateYear(request.getReleasedDateYear())
                .runtimeMinute(request.getRuntimeMinute())
                .story(request.getStory())
                .imageUri(request.getImageUri())
                .haveBeenSeen(request.isHaveBeenSeen())
                .ratingValue(request.getRatingValue())
                .personalRatingValue(request.getPersonalRatingValue())
                .build();
    }

    public MovieResponse toResponse(Movie movie) {
        return MovieResponse.builder()
                .id(movie.getId())
                .imdbId(movie.getImdbId())
                .title(movie.getTitle())
                .originalTitle(movie.getOriginalTitle())
                .releasedDateYear(movie.getReleasedDateYear())
                .runtimeMinute(movie.getRuntimeMinute())
                .story(movie.getStory())
                .imageUri(movie.getImageUri())
                .haveBeenSeen(movie.isHaveBeenSeen())
                .ratingValue(movie.getRatingValue())
                .personalRatingValue(movie.getPersonalRatingValue())
                .build();
    }
}
