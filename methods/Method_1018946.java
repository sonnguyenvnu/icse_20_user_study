@Override public void finished(MediaPlayer mediaPlayer){
  if (mediaPlayer.controls().getRepeat()) {
    mediaPlayer.submit(new ReplayMediaTask(mediaPlayer));
  }
}
