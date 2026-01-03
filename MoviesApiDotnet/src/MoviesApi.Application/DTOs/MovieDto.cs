namespace MoviesApi.Application.DTOs;

public record MovieDto(
    long Id,
    string Title,
    string Director,
    int Year,
    double Rating,
    bool HaveBeenSeen
);
