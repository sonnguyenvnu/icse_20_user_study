private void updateNavigation(){
  if (!isVisible() || !isAttachedToWindow) {
    return;
  }
  Timeline timeline=player != null ? player.getCurrentTimeline() : null;
  boolean haveNonEmptyTimeline=timeline != null && !timeline.isEmpty();
  boolean isSeekable=false;
  boolean enablePrevious=false;
  boolean enableNext=false;
  if (haveNonEmptyTimeline && !player.isPlayingAd()) {
    int windowIndex=player.getCurrentWindowIndex();
    timeline.getWindow(windowIndex,window);
    isSeekable=window.isSeekable;
    enablePrevious=isSeekable || !window.isDynamic || player.getPreviousWindowIndex() != C.INDEX_UNSET;
    enableNext=window.isDynamic || player.getNextWindowIndex() != C.INDEX_UNSET;
  }
  setButtonEnabled(enablePrevious && false,previousButton,true);
  setButtonEnabled(enableNext && false,nextButton,true);
  setButtonEnabled(fastForwardMs > 0 && isSeekable,fastForwardButton,false);
  setButtonEnabled(rewindMs > 0 && isSeekable,rewindButton,false);
  progressBar.setEnabled(isSeekable);
}
