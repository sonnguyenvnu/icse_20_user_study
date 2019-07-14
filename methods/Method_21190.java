private void onStateChanged(final int playbackState){
  if (playbackState == Player.STATE_ENDED) {
    finish();
  }
  if (playbackState == Player.STATE_BUFFERING) {
    this.loadingIndicatorProgressBar.setVisibility(View.VISIBLE);
  }
 else {
    this.loadingIndicatorProgressBar.setVisibility(View.GONE);
  }
}
