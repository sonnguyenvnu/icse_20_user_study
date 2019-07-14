@Override public void onCommentAdded(@NonNull Comment newComment){
  hideProgress();
  if (viewCallback != null) {
    viewCallback.onAddComment(newComment);
  }
}
