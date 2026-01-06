import {Component, inject} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import {AuthService} from './services/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, MatToolbarModule, MatIconModule],
  template: `
    <mat-toolbar color="primary">
      <mat-icon>movie</mat-icon>
      <span class="title">Gestion de Films</span>
      @if (isAuthenticated()) {
        <div class="user-info">
          <span class="username">👤 {{ currentUser()?.username }}</span>
          <button
            class="btn-logout"
            (click)="onLogout()"
            type="button">
            Déconnexion
          </button>
        </div>
      }
    </mat-toolbar>
    <router-outlet></router-outlet>
  `,
  styles: [`
    .title {
      margin-left: 12px;
      font-size: 20px;
    }
  `]
})
export class AppComponent {
  title = 'movies-app';

  authService = inject(AuthService);

  // Exposer les computed signals pour le template
  isAuthenticated = this.authService.isAuthenticated;
  currentUser = this.authService.currentUser;

  onLogout(): void {
    this.authService.logout();
  }
}
