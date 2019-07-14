@Override public void onSongSetAsFavorite(@NonNull Song song){
  buttonFavoriteToggle.setEnabled(true);
  updateFavoriteToggle(song.isFavorite());
}
