@Override public void onPlayStatusChanged(boolean isPlaying){
  updatePlayToggle(isPlaying);
  if (isPlaying) {
    imageViewAlbum.resumeRotateAnimation();
    mHandler.removeCallbacks(mProgressCallback);
    mHandler.post(mProgressCallback);
  }
 else {
    imageViewAlbum.pauseRotateAnimation();
    mHandler.removeCallbacks(mProgressCallback);
  }
}
