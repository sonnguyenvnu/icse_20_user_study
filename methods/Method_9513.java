public void onResume(){
  redraw(0);
  if (videoPlayer != null) {
    videoPlayer.seekTo(videoPlayer.getCurrentPosition() + 1);
  }
}
