namespace MoviesApi.Domain.Entities;

public class Movie
{
    public long Id { get; set; }
    public string Title { get; set; } = string.Empty;
    public string Director { get; set; } = string.Empty;
    public int Year { get; set; }
    public double Rating { get; set; }
    public bool HaveBeenSeen { get; set; }
}
