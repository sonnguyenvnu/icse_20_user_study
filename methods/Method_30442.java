@Override public void onSkipToQueueItem(Player player,long id){
  Timeline timeline=player.getCurrentTimeline();
  if (timeline.isEmpty()) {
    return;
  }
  int windowIndex=(int)id;
  if (windowIndex < 0 || windowIndex >= timeline.getWindowCount()) {
    return;
  }
  player.seekToDefaultPosition(windowIndex);
  player.setPlayWhenReady(true);
}
