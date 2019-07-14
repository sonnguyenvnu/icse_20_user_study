@Override @Nullable public final Object getCurrentTag(){
  int windowIndex=getCurrentWindowIndex();
  Timeline timeline=getCurrentTimeline();
  return windowIndex >= timeline.getWindowCount() ? null : timeline.getWindow(windowIndex,window,true).tag;
}
