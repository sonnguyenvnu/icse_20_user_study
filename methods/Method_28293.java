@Override public void addComment(@NonNull TimelineModel timelineModel){
  onHideBlockingProgress();
  adapter.addItem(timelineModel);
  if (commentsCallback != null)   commentsCallback.onClearEditText();
}
