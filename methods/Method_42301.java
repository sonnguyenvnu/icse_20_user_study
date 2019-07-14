private void next(){
  Timeline currentTimeline=player.getCurrentTimeline();
  if (currentTimeline == null) {
    return;
  }
  int currentWindowIndex=player.getCurrentWindowIndex();
  if (currentWindowIndex < currentTimeline.getWindowCount() - 1) {
    player.seekToDefaultPosition(currentWindowIndex + 1);
  }
 else   if (currentTimeline.getWindow(currentWindowIndex,window,false).isDynamic) {
    player.seekToDefaultPosition();
  }
}
