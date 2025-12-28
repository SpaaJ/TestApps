export interface Movie {
  id?: number;
  IMDbId: string;
  title: string;
  originalTitle: string;
  releasedDateYear: number;
  runtimeMinute: number;
  story: string;
  imageUri: string;
  haveBeenSeen: boolean;
  RatingValue: number;
  PersonalRatingValue: number | null;
}
