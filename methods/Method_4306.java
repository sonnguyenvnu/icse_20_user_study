@Override public final int getNextWindowIndex(){
  Timeline timeline=getCurrentTimeline();
  return timeline.isEmpty() ? C.INDEX_UNSET : timeline.getNextWindowIndex(getCurrentWindowIndex(),getRepeatModeForNavigation(),getShuffleModeEnabled());
}
