export interface Movie {
  id?: number;
  imdbId: string;              // ← minuscule
  title: string;
  originalTitle: string;
  releasedDateYear: number;
  runtimeMinute: number;
  story: string;
  imageUri: string;
  haveBeenSeen: boolean;
  ratingValue: number;         // ← minuscule
  personalRatingValue: number | null; // ← minuscule
}
