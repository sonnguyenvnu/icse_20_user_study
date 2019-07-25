private void quit(){
  pause();
  playingNotification.stop();
  closeAudioEffectSession();
  getAudioManager().abandonAudioFocus(audioFocusListener);
  stopSelf();
}
