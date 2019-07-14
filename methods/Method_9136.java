public void setStreamType(int type){
  if (player != null) {
    player.setAudioStreamType(type);
  }
  if (audioPlayer != null) {
    audioPlayer.setAudioStreamType(type);
  }
}
