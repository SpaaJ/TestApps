import { Component, OnInit, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatChipsModule } from '@angular/material/chips';
import { MatCardModule } from '@angular/material/card';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

import { MovieService } from '../../services/movie.service';
import { Movie } from '../../models/movie.model';

@Component({
  selector: 'app-movie-list',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatChipsModule,
    MatCardModule,
    MatTooltipModule,
    MatSnackBarModule
  ],
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.scss']
})
export class MovieListComponent implements OnInit {
  movies = signal<Movie[]>([]);
  isLoading = signal(false);

  displayedColumns: string[] = ['title', 'releasedDateYear', 'runtimeMinute', 'RatingValue', 'haveBeenSeen', 'actions'];

  constructor(
    private movieService: MovieService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {
    console.log('🎬 MovieListComponent créé');
  }

  ngOnInit(): void {
    console.log('🎬 MovieListComponent initialisé');
    this.loadMovies();
  }

  loadMovies(): void {
    console.log('🔄 Début du chargement');
    this.isLoading.set(true);
    console.log('🔄 isLoading =', this.isLoading());

    this.movieService.getAllMovies().subscribe({
      next: (data) => {
        console.log('✅ Réponse reçue:', data);
        this.movies.set(data);
        this.isLoading.set(false);
        console.log('✅ movies.length =', this.movies().length);
        console.log('✅ isLoading =', this.isLoading());
      },
      error: (error) => {
        console.error('❌ ERREUR:', error);
        this.showMessage('Erreur lors du chargement des films', 'error');
        this.movies.set([]);
        this.isLoading.set(false);
      }
    });
  }

  createMovie(): void {
    this.router.navigate(['/movies/new']);
  }

  viewMovie(id: number): void {
    this.router.navigate(['/movies', id]);
  }

  deleteMovie(id: number, event: Event): void {
    event.stopPropagation();

    const movie = this.movies().find(m => m.id === id);
    const movieTitle = movie ? movie.title : 'ce film';

    if (confirm(`Êtes-vous sûr de vouloir supprimer "${movieTitle}" ?`)) {
      this.movieService.deleteMovie(id).subscribe({
        next: () => {
          this.showMessage('Film supprimé avec succès', 'success');
          this.loadMovies();
        },
        error: (error) => {
          console.error('❌ Erreur lors de la suppression:', error);
          this.showMessage('Erreur lors de la suppression', 'error');
        }
      });
    }
  }

  private showMessage(message: string, type: 'success' | 'error'): void {
    this.snackBar.open(message, 'Fermer', {
      duration: 3000,
      horizontalPosition: 'end',
      verticalPosition: 'top',
      panelClass: type === 'success' ? 'snackbar-success' : 'snackbar-error'
    });
  }
}
