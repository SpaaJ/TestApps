namespace MoviesApi.Application.DTOs;

using System.ComponentModel.DataAnnotations;

public record CreateMovieDto(
    [Required, StringLength(200)] string Title,
    [Required, StringLength(100)] string Director,
    [Range(1888, 2100)] int Year,
    [Range(0.0, 10.0)] double Rating,
    bool HaveBeenSeen
);
