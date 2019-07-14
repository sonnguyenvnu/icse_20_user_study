@Override public final int getPreviousWindowIndex(){
  Timeline timeline=getCurrentTimeline();
  return timeline.isEmpty() ? C.INDEX_UNSET : timeline.getPreviousWindowIndex(getCurrentWindowIndex(),getRepeatModeForNavigation(),getShuffleModeEnabled());
}
