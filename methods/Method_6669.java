public void playPreviousMessage(){
  ArrayList<MessageObject> currentPlayList=SharedConfig.shuffleMusic ? shuffledPlaylist : playlist;
  if (currentPlayList.isEmpty() || currentPlaylistNum < 0 || currentPlaylistNum >= currentPlayList.size()) {
    return;
  }
  MessageObject currentSong=currentPlayList.get(currentPlaylistNum);
  if (currentSong.audioProgressSec > 10) {
    seekToProgress(currentSong,0);
    return;
  }
  if (SharedConfig.playOrderReversed) {
    currentPlaylistNum--;
    if (currentPlaylistNum < 0) {
      currentPlaylistNum=currentPlayList.size() - 1;
    }
  }
 else {
    currentPlaylistNum++;
    if (currentPlaylistNum >= currentPlayList.size()) {
      currentPlaylistNum=0;
    }
  }
  if (currentPlaylistNum < 0 || currentPlaylistNum >= currentPlayList.size()) {
    return;
  }
  playMusicAgain=true;
  playMessage(currentPlayList.get(currentPlaylistNum));
}
