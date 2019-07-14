@Override public void setPlayWhenReady(boolean playWhenReady){
  if (getPlayWhenReady() == playWhenReady) {
    return;
  }
  if (playWhenReady) {
    requestAudioFocus();
  }
 else {
    abandonAudioFocus();
  }
}
