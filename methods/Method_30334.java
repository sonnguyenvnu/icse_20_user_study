public static Intent makeIntent(long movieId,Context context){
  return new Intent(context,MovieActivity.class).putExtra(EXTRA_MOVIE_ID,movieId);
}
