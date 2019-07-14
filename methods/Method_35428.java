public void onSongUpdated(@Nullable Song song){
  if (song == null) {
    imageViewAlbum.cancelRotateAnimation();
    buttonPlayToggle.setImageResource(R.drawable.ic_play);
    seekBarProgress.setProgress(0);
    updateProgressTextWithProgress(0);
    seekTo(0);
    mHandler.removeCallbacks(mProgressCallback);
    return;
  }
  textViewName.setText(song.getDisplayName());
  textViewArtist.setText(song.getArtist());
  buttonFavoriteToggle.setImageResource(song.isFavorite() ? R.drawable.ic_favorite_yes : R.drawable.ic_favorite_no);
  textViewDuration.setText(TimeUtils.formatDuration(song.getDuration()));
  Bitmap bitmap=AlbumUtils.parseAlbum(song);
  if (bitmap == null) {
    imageViewAlbum.setImageResource(R.drawable.default_record_album);
  }
 else {
    imageViewAlbum.setImageBitmap(AlbumUtils.getCroppedBitmap(bitmap));
  }
  imageViewAlbum.pauseRotateAnimation();
  mHandler.removeCallbacks(mProgressCallback);
  if (mPlayer.isPlaying()) {
    imageViewAlbum.startRotateAnimation();
    mHandler.post(mProgressCallback);
    buttonPlayToggle.setImageResource(R.drawable.ic_pause);
  }
}
