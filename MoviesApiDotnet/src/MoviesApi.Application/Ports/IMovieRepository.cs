namespace MoviesApi.Application.Ports;

using MoviesApi.Domain.Entities;

public interface IMovieRepository
{
    Task<IEnumerable<Movie>> GetAllAsync();
    Task<Movie?> GetByIdAsync(long id);
    Task<Movie> CreateAsync(Movie movie);
    Task<Movie> UpdateAsync(Movie movie);
    Task DeleteAsync(long id);
    Task<bool> ExistsAsync(long id);
}
