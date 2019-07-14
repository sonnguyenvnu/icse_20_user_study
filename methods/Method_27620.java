@Override public void onAddNewComment(@NonNull Comment comment){
  hideBlockingProgress();
  adapter.addItem(comment);
  if (commentsCallback != null)   commentsCallback.onClearEditText();
}
