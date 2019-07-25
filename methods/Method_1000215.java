public void back(boolean force){
  if (getSongProgressMillis() > 5000) {
    seek(0);
  }
 else {
    playPreviousSong(force);
  }
}
