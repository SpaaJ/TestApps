namespace MoviesApi.WebApi.Controllers;

using Microsoft.AspNetCore.Mvc;
using MoviesApi.Application.DTOs;
using MoviesApi.Application.Ports;
using MoviesApi.Domain.Exceptions;

[ApiController]
[Route("api/[controller]")]
public class MoviesController : ControllerBase
{
    private readonly IMovieService _movieService;

    public MoviesController(IMovieService movieService)
    {
        _movieService = movieService;
    }

    [HttpGet]
    public async Task<ActionResult<IEnumerable<MovieDto>>> GetAll()
    {
        var movies = await _movieService.GetAllMoviesAsync();
        return Ok(movies);
    }

    [HttpGet("{id}")]
    public async Task<ActionResult<MovieDto>> GetById(long id)
    {
        try
        {
            var movie = await _movieService.GetMovieByIdAsync(id);
            return Ok(movie);
        }
        catch (MovieNotFoundException ex)
        {
            return NotFound(new { message = ex.Message });
        }
    }

    [HttpPost]
    public async Task<ActionResult<MovieDto>> Create([FromBody] CreateMovieDto dto)
    {
        var created = await _movieService.CreateMovieAsync(dto);
        return CreatedAtAction(nameof(GetById), new { id = created.Id }, created);
    }

    [HttpPut("{id}")]
    public async Task<ActionResult<MovieDto>> Update(long id, [FromBody] CreateMovieDto dto)
    {
        try
        {
            var updated = await _movieService.UpdateMovieAsync(id, dto);
            return Ok(updated);
        }
        catch (MovieNotFoundException ex)
        {
            return NotFound(new { message = ex.Message });
        }
    }

    [HttpDelete("{id}")]
    public async Task<IActionResult> Delete(long id)
    {
        try
        {
            await _movieService.DeleteMovieAsync(id);
            return NoContent();
        }
        catch (MovieNotFoundException ex)
        {
            return NotFound(new { message = ex.Message });
        }
    }
}
