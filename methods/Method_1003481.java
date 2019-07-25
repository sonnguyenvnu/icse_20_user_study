@ReactMethod public void stop(final Double key,final Callback callback){
  MediaPlayer player=this.playerPool.get(key);
  if (player != null && player.isPlaying()) {
    player.pause();
    player.seekTo(0);
  }
  if (!this.mixWithOthers && key == this.focusedPlayerKey) {
    AudioManager audioManager=(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
    audioManager.abandonAudioFocus(this);
  }
  callback.invoke();
}
