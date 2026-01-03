namespace MoviesApi.Infrastructure.Configurations;

using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using MoviesApi.Domain.Entities;

public class MovieConfiguration : IEntityTypeConfiguration<Movie>
{
    public void Configure(EntityTypeBuilder<Movie> builder)
    {
        builder.ToTable("movies");

        builder.HasKey(m => m.Id);

        builder.Property(m => m.Id)
            .HasColumnName("id")
            .ValueGeneratedOnAdd();

        builder.Property(m => m.Title)
            .HasColumnName("title")
            .IsRequired()
            .HasMaxLength(200);

        builder.Property(m => m.Director)
            .HasColumnName("director")
            .IsRequired()
            .HasMaxLength(100);

        builder.Property(m => m.Year)
            .HasColumnName("year")
            .IsRequired();

        builder.Property(m => m.Rating)
            .HasColumnName("rating")
            .IsRequired();

        builder.Property(m => m.HaveBeenSeen)
            .HasColumnName("have_been_seen")
            .IsRequired();
    }
}
