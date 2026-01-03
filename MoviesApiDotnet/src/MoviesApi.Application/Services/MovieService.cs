namespace MoviesApi.Application.Services;

using MoviesApi.Application.DTOs;
using MoviesApi.Application.Ports;
using MoviesApi.Domain.Entities;
using MoviesApi.Domain.Exceptions;

public class MovieService : IMovieService
{
    private readonly IMovieRepository _repository;

    public MovieService(IMovieRepository repository)
    {
        _repository = repository;
    }

    public async Task<IEnumerable<MovieDto>> GetAllMoviesAsync()
    {
        var movies = await _repository.GetAllAsync();
        return movies.Select(MapToDto);
    }

    public async Task<MovieDto> GetMovieByIdAsync(long id)
    {
        var movie = await _repository.GetByIdAsync(id)
            ?? throw new MovieNotFoundException(id);
        
        return MapToDto(movie);
    }

    public async Task<MovieDto> CreateMovieAsync(CreateMovieDto dto)
    {
        var movie = new Movie
        {
            Title = dto.Title,
            Director = dto.Director,
            Year = dto.Year,
            Rating = dto.Rating,
            HaveBeenSeen = dto.HaveBeenSeen
        };

        var created = await _repository.CreateAsync(movie);
        return MapToDto(created);
    }

    public async Task<MovieDto> UpdateMovieAsync(long id, CreateMovieDto dto)
    {
        var existing = await _repository.GetByIdAsync(id)
            ?? throw new MovieNotFoundException(id);

        existing.Title = dto.Title;
        existing.Director = dto.Director;
        existing.Year = dto.Year;
        existing.Rating = dto.Rating;
        existing.HaveBeenSeen = dto.HaveBeenSeen;

        var updated = await _repository.UpdateAsync(existing);
        return MapToDto(updated);
    }

    public async Task DeleteMovieAsync(long id)
    {
        if (!await _repository.ExistsAsync(id))
            throw new MovieNotFoundException(id);

        await _repository.DeleteAsync(id);
    }

    private static MovieDto MapToDto(Movie movie) => new(
        movie.Id,
        movie.Title,
        movie.Director,
        movie.Year,
        movie.Rating,
        movie.HaveBeenSeen
    );
}
