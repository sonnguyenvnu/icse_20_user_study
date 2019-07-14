@Override public void dismissInternal(){
  super.dismissInternal();
  if (commentTextView != null) {
    commentTextView.onDestroy();
  }
}
