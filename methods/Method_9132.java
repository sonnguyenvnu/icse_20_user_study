public void play(){
  mixedPlayWhenReady=true;
  if (mixedAudio) {
    if (!audioPlayerReady || !videoPlayerReady) {
      if (player != null) {
        player.setPlayWhenReady(false);
      }
      if (audioPlayer != null) {
        audioPlayer.setPlayWhenReady(false);
      }
      return;
    }
  }
  if (player != null) {
    player.setPlayWhenReady(true);
  }
  if (audioPlayer != null) {
    audioPlayer.setPlayWhenReady(true);
  }
}
