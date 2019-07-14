public void setMute(boolean value){
  if (player != null) {
    player.setVolume(value ? 0.0f : 1.0f);
  }
  if (audioPlayer != null) {
    audioPlayer.setVolume(value ? 0.0f : 1.0f);
  }
}
