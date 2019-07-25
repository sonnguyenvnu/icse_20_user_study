public void play(){
  mAudioManager.setMode(AudioManager.MODE_NORMAL);
  if (mMediaPlayer != null)   mMediaPlayer.start();
}
