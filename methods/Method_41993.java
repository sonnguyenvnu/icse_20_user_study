private void releasePlayer(){
  if (player != null) {
    shouldAutoPlay=player.getPlayWhenReady();
    updateResumePosition();
    player.release();
    player=null;
    trackSelector=null;
    trackSelectionHelper=null;
  }
}
