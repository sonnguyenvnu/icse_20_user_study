public boolean setPlaylist(ArrayList<MessageObject> messageObjects,MessageObject current,boolean loadMusic){
  if (playingMessageObject == current) {
    return playMessage(current);
  }
  forceLoopCurrentPlaylist=!loadMusic;
  playMusicAgain=!playlist.isEmpty();
  playlist.clear();
  for (int a=messageObjects.size() - 1; a >= 0; a--) {
    MessageObject messageObject=messageObjects.get(a);
    if (messageObject.isMusic()) {
      playlist.add(messageObject);
    }
  }
  currentPlaylistNum=playlist.indexOf(current);
  if (currentPlaylistNum == -1) {
    playlist.clear();
    shuffledPlaylist.clear();
    currentPlaylistNum=playlist.size();
    playlist.add(current);
  }
  if (current.isMusic()) {
    if (SharedConfig.shuffleMusic) {
      buildShuffledPlayList();
      currentPlaylistNum=0;
    }
    if (loadMusic) {
      DataQuery.getInstance(current.currentAccount).loadMusic(current.getDialogId(),playlist.get(0).getIdWithChannel());
    }
  }
  return playMessage(current);
}
