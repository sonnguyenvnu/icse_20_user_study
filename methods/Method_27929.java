@Override public void addComment(@NonNull Comment newComment){
  hideBlockingProgress();
  if (adapter != null) {
    adapter.addItem(TimelineModel.constructComment(newComment));
  }
  if (commentsCallback != null)   commentsCallback.onClearEditText();
}
