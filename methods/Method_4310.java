@Override public final long getContentDuration(){
  Timeline timeline=getCurrentTimeline();
  return timeline.isEmpty() ? C.TIME_UNSET : timeline.getWindow(getCurrentWindowIndex(),window).getDurationMs();
}
