namespace MoviesApi.Domain.Exceptions;

public class MovieNotFoundException : Exception
{
    public MovieNotFoundException(long id) 
        : base($"Movie with ID {id} not found")
    {
    }
}
