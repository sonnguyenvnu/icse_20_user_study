@Override public void finished(MediaPlayer mediaPlayer){
  mediaPlayer.submit(new ResetMediaTask(mediaPlayer));
}
