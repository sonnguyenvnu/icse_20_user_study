@Override public void subscribe(){
  bindPlaybackService();
  retrieveLastPlayMode();
  if (mPlaybackService != null && mPlaybackService.isPlaying()) {
    mView.onSongUpdated(mPlaybackService.getPlayingSong());
  }
 else {
  }
}
