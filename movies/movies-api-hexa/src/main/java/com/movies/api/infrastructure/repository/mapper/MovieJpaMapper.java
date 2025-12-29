package com.movies.api.infrastructure.repository.mapper;

import com.movies.api.domain.model.Movie;
import com.movies.api.infrastructure.repository.entity.MovieJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class MovieJpaMapper {

    public MovieJpaEntity toEntity(Movie movie) {
        return MovieJpaEntity.builder()
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

    public Movie toDomain(MovieJpaEntity entity) {
        return Movie.builder()
                .id(entity.getId())
                .imdbId(entity.getImdbId())
                .title(entity.getTitle())
                .originalTitle(entity.getOriginalTitle())
                .releasedDateYear(entity.getReleasedDateYear())
                .runtimeMinute(entity.getRuntimeMinute())
                .story(entity.getStory())
                .imageUri(entity.getImageUri())
                .haveBeenSeen(entity.isHaveBeenSeen())
                .ratingValue(entity.getRatingValue())
                .personalRatingValue(entity.getPersonalRatingValue())
                .build();
    }
}
