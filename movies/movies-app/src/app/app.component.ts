import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, MatToolbarModule, MatIconModule],
  template: `
    <mat-toolbar color="primary">
      <mat-icon>movie</mat-icon>
      <span class="title">Gestion de Films</span>
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
}
