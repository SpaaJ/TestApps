export interface LoginRequest {
  username: string;
  password: string;
}

export interface AuthResponse {
  token: string;
  expiresIn: number; // en secondes
}

export interface User {
  username: string;
  token: string;
  expiresAt: number; // timestamp
}
