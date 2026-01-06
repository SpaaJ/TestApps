import { Injectable, signal, computed } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, throwError, tap, catchError } from 'rxjs';
import { LoginRequest, AuthResponse, User } from '../models/auth.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly AUTH_URL = 'http://localhost:8080/auth';
  private readonly TOKEN_KEY = '::AUTH_TOKEN::';
  private readonly USER_KEY = '::AUTH_USER::';

  // Signal pour l'utilisateur courant
  private currentUserSignal = signal<User | null>(this.loadUserFromStorage());

  // Computed signal pour savoir si l'utilisateur est authentifié
  isAuthenticated = computed(() => {
    const user = this.currentUserSignal();
    if (!user) return false;

    // Vérifier si le token n'est pas expiré
    return user.expiresAt > Date.now();
  });

  // Computed signal pour obtenir l'utilisateur
  currentUser = computed(() => this.currentUserSignal());

  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    // Vérifier l'expiration du token au démarrage
    this.checkTokenExpiration();
  }

  /**
   * Connexion de l'utilisateur
   */
  login(credentials: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.AUTH_URL}/login`, credentials)
      .pipe(
        tap(response => this.handleAuthResponse(credentials.username, response)),
        catchError(this.handleError)
      );
  }

  /**
   * Déconnexion de l'utilisateur
   */
  logout(): void {
    this.clearAuthData();
    this.router.navigate(['/login']);
  }

  /**
   * Obtenir le token JWT
   */
  getToken(): string | null {
    const user = this.currentUserSignal();
    if (!user || user.expiresAt <= Date.now()) {
      this.clearAuthData();
      return null;
    }
    return user.token;
  }

  /**
   * Gérer la réponse d'authentification
   */
  private handleAuthResponse(username: string, response: AuthResponse): void {
    const expiresAt = Date.now() + (response.expiresIn * 1000);

    const user: User = {
      username,
      token: response.token,
      expiresAt
    };

    // Stocker dans sessionStorage
    sessionStorage.setItem(this.USER_KEY, JSON.stringify(user));

    // Mettre à jour le signal
    this.currentUserSignal.set(user);
  }

  /**
   * Charger l'utilisateur depuis le storage
   */
  private loadUserFromStorage(): User | null {
    try {
      const userJson = sessionStorage.getItem(this.USER_KEY);
      if (!userJson) return null;

      const user: User = JSON.parse(userJson);

      // Vérifier si le token n'est pas expiré
      if (user.expiresAt <= Date.now()) {
        this.clearAuthData();
        return null;
      }

      return user;
    } catch (error) {
      console.error('Erreur lors du chargement de l\'utilisateur:', error);
      this.clearAuthData();
      return null;
    }
  }

  /**
   * Effacer les données d'authentification
   */
  private clearAuthData(): void {
    sessionStorage.removeItem(this.USER_KEY);
    this.currentUserSignal.set(null);
  }

  /**
   * Vérifier périodiquement l'expiration du token
   */
  private checkTokenExpiration(): void {
    setInterval(() => {
      const user = this.currentUserSignal();
      if (user && user.expiresAt <= Date.now()) {
        console.warn('Token expiré, déconnexion automatique');
        this.logout();
      }
    }, 60000); // Vérifier toutes les minutes
  }

  /**
   * Gestion des erreurs HTTP
   */
  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'Une erreur est survenue lors de l\'authentification';

    if (error.status === 401) {
      errorMessage = 'Identifiants invalides';
    } else if (error.error instanceof ErrorEvent) {
      errorMessage = `Erreur: ${error.error.message}`;
    } else {
      errorMessage = `Erreur serveur: ${error.status}`;
    }

    console.error('Erreur d\'authentification:', error);
    return throwError(() => new Error(errorMessage));
  }
}
