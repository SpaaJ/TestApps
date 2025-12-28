import { Component, OnInit, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDividerModule } from '@angular/material/divider';

import { MovieService } from '../../services/movie.service';
import { Movie } from '../../models/movie.model';

@Component({
  selector: 'app-movie-detail',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatSlideToggleModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    MatDividerModule
  ],
  templateUrl: './movie-detail.component.html',
  styleUrls: ['./movie-detail.component.scss']
})
export class MovieDetailComponent implements OnInit {
  movieForm: FormGroup;

  // Signals
  isLoading = signal(false);
  isEditMode = signal(false);
  movieId = signal<number | null>(null);

  // Computed signal pour le titre de la page
  pageTitle = computed(() =>
    this.isEditMode() ? 'Modifier le Film' : 'Nouveau Film'
  );

  constructor(
    private fb: FormBuilder,
    private movieService: MovieService,
    private route: ActivatedRoute,
    private router: Router,
    private snackBar: MatSnackBar
  ) {
    this.movieForm = this.fb.group({
      // Identifiants
      IMDbId: ['', [Validators.required, Validators.pattern(/^tt\d{7,8}$/)]],

      // Titres
      title: ['', [Validators.required, Validators.minLength(1)]],
      originalTitle: ['', [Validators.required, Validators.minLength(1)]],

      // Informations temporelles
      releasedDateYear: ['', [Validators.required, Validators.min(1888), Validators.max(2100)]],
      runtimeMinute: ['', [Validators.required, Validators.min(1), Validators.max(1000)]],

      // Contenu
      story: ['', [Validators.required, Validators.minLength(10)]],
      imageUri: ['', [Validators.required, Validators.pattern(/^https?:\/\/.+/)]],

      // Statut et évaluations
      haveBeenSeen: [false],
      RatingValue: ['', [Validators.required, Validators.min(0), Validators.max(10)]],
      PersonalRatingValue: [null, [Validators.min(0), Validators.max(10)]]
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id && id !== 'new') {
      this.isEditMode.set(true);
      this.movieId.set(+id);
      this.loadMovie(+id);
    }
  }

  loadMovie(id: number): void {
    this.isLoading.set(true);

    this.movieService.getMovieById(id).subscribe({
      next: (movie) => {
        this.movieForm.patchValue(movie);
        this.isLoading.set(false);
      },
      error: (error) => {
        console.error('❌ Erreur lors du chargement:', error);
        this.showMessage('Erreur lors du chargement du film', 'error');
        this.isLoading.set(false);
        this.router.navigate(['/movies']);
      }
    });
  }

  onSubmit(): void {
    if (this.movieForm.valid) {
      this.isLoading.set(true);
      const movieData: Movie = {
        ...this.movieForm.value,
        // Convertir null en null explicitement pour PersonalRatingValue
        PersonalRatingValue: this.movieForm.value.PersonalRatingValue || null
      };

      const operation = this.isEditMode()
        ? this.movieService.updateMovie(this.movieId()!, movieData)
        : this.movieService.createMovie(movieData);

      operation.subscribe({
        next: () => {
          const message = this.isEditMode()
            ? 'Film modifié avec succès'
            : 'Film créé avec succès';
          this.showMessage(message, 'success');
          this.router.navigate(['/movies']);
        },
        error: (error) => {
          console.error('❌ Erreur lors de la sauvegarde:', error);
          this.showMessage('Erreur lors de la sauvegarde', 'error');
          this.isLoading.set(false);
        }
      });
    } else {
      this.showMessage('Veuillez remplir tous les champs requis correctement', 'error');
      this.movieForm.markAllAsTouched();
    }
  }

  onCancel(): void {
    this.router.navigate(['/movies']);
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
