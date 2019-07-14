@Override public void addNewComment(@NonNull TimelineModel timelineModel){
  onHideBlockingProgress();
  adapter.addItem(timelineModel);
  if (commentsCallback != null)   commentsCallback.onClearEditText();
}
