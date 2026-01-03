namespace MoviesApi.Application.Ports;

using MoviesApi.Application.DTOs;

public interface IMovieService
{
    Task<IEnumerable<MovieDto>> GetAllMoviesAsync();
    Task<MovieDto> GetMovieByIdAsync(long id);
    Task<MovieDto> CreateMovieAsync(CreateMovieDto dto);
    Task<MovieDto> UpdateMovieAsync(long id, CreateMovieDto dto);
    Task DeleteMovieAsync(long id);
}
