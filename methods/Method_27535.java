@Override public void onSubscribed(boolean cancelable){
  sendToView(v -> {
    if (cancelable) {
      v.showProgress(R.string.in_progress);
    }
 else {
      v.showBlockingProgress(R.string.in_progress);
    }
  }
);
}
