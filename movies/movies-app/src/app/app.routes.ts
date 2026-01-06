// import { Routes } from '@angular/router';
// import { MovieListComponent } from './components/movie-list/movie-list.component';
// import { MovieDetailComponent } from './components/movie-detail/movie-detail.component';
//
// export const routes: Routes = [
//   { path: '', redirectTo: '/movies', pathMatch: 'full' },
//   { path: 'movies', component: MovieListComponent },
//   { path: 'movies/new', component: MovieDetailComponent },
//   { path: 'movies/:id', component: MovieDetailComponent },
//   { path: '**', redirectTo: '/movies' }
// ];


import { Routes } from '@angular/router';
import { MovieListComponent } from './components/movie-list/movie-list.component';
import { MovieDetailComponent } from './components/movie-detail/movie-detail.component';
import { LoginComponent } from './components/login/login.component';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'movies',
    canActivate: [authGuard],
    children: [
      {
        path: '',
        component: MovieListComponent
      },
      {
        path: ':id',
        component: MovieDetailComponent
      }
    ]
  },
  {
    path: '',
    redirectTo: '/movies',
    pathMatch: 'full'
  },
  {
    path: '**',
    redirectTo: '/movies'
  }
];
