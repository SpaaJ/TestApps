import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';
import {HttpInterceptorFn} from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const token = authService.getToken();

  // Ne pas ajouter le token pour les requêtes d'authentification
  if (req.url.includes('/auth/login')) {
    return next(req);
  }

  // Ajouter le token si disponible
  if (token) {
    const clonedRequest = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next(clonedRequest);
  }

  return next(req);
};
