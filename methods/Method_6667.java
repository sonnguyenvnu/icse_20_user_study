public void playMessageAtIndex(int index){
  if (currentPlaylistNum < 0 || currentPlaylistNum >= playlist.size()) {
    return;
  }
  currentPlaylistNum=index;
  playMusicAgain=true;
  if (playingMessageObject != null) {
    playingMessageObject.resetPlayingProgress();
  }
  playMessage(playlist.get(currentPlaylistNum));
}
