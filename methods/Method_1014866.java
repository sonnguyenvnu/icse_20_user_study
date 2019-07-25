public void reset(){
  lastKnownWindow=player.getCurrentWindowIndex();
  lastKnownPosition=player.getCurrentPosition();
  player.stop(true);
  player.setPlayWhenReady(false);
}
