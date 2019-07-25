public void error(){
  if (currentPlayer != null) {
    currentPlayer.getController().onPlayStateChanged(DefaultVideoPlayer.PLAY_STATE_ERROR);
  }
}
