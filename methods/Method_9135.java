public boolean isPlaying(){
  return mixedAudio && mixedPlayWhenReady || player != null && player.getPlayWhenReady();
}
