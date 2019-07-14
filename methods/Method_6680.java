public void toggleShuffleMusic(int type){
  boolean oldShuffle=SharedConfig.shuffleMusic;
  SharedConfig.toggleShuffleMusic(type);
  if (oldShuffle != SharedConfig.shuffleMusic) {
    if (SharedConfig.shuffleMusic) {
      buildShuffledPlayList();
      currentPlaylistNum=0;
    }
 else {
      if (playingMessageObject != null) {
        currentPlaylistNum=playlist.indexOf(playingMessageObject);
        if (currentPlaylistNum == -1) {
          playlist.clear();
          shuffledPlaylist.clear();
          cleanupPlayer(true,true);
        }
      }
    }
  }
}
