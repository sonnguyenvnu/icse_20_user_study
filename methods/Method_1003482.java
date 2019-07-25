@ReactMethod public void release(final Double key){
  MediaPlayer player=this.playerPool.get(key);
  if (player != null) {
    player.reset();
    player.release();
    this.playerPool.remove(key);
    if (!this.mixWithOthers && key == this.focusedPlayerKey) {
      AudioManager audioManager=(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
      audioManager.abandonAudioFocus(this);
    }
  }
}
