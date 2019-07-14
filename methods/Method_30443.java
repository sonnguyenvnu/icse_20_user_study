@Override public void onSkipToNext(Player player){
  if (player.getCurrentTimeline().isEmpty()) {
    return;
  }
  int nextWindowIndex=player.getNextWindowIndex();
  if (nextWindowIndex == C.INDEX_UNSET) {
    nextWindowIndex=player.getCurrentWindowIndex();
  }
  player.seekToDefaultPosition(nextWindowIndex);
  player.setPlayWhenReady(true);
}
