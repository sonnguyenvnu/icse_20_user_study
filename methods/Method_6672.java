private void checkIsNextMusicFileDownloaded(int currentAccount){
  if (!DownloadController.getInstance(currentAccount).canDownloadNextTrack()) {
    return;
  }
  ArrayList<MessageObject> currentPlayList=SharedConfig.shuffleMusic ? shuffledPlaylist : playlist;
  if (currentPlayList == null || currentPlayList.size() < 2) {
    return;
  }
  int nextIndex;
  if (SharedConfig.playOrderReversed) {
    nextIndex=currentPlaylistNum + 1;
    if (nextIndex >= currentPlayList.size()) {
      nextIndex=0;
    }
  }
 else {
    nextIndex=currentPlaylistNum - 1;
    if (nextIndex < 0) {
      nextIndex=currentPlayList.size() - 1;
    }
  }
  if (nextIndex < 0 || nextIndex >= currentPlayList.size()) {
    return;
  }
  MessageObject nextAudio=currentPlayList.get(nextIndex);
  File file=null;
  if (!TextUtils.isEmpty(nextAudio.messageOwner.attachPath)) {
    file=new File(nextAudio.messageOwner.attachPath);
    if (!file.exists()) {
      file=null;
    }
  }
  final File cacheFile=file != null ? file : FileLoader.getPathToMessage(nextAudio.messageOwner);
  boolean exist=cacheFile != null && cacheFile.exists();
  if (cacheFile != null && cacheFile != file && !cacheFile.exists() && nextAudio.isMusic()) {
    FileLoader.getInstance(currentAccount).loadFile(nextAudio.getDocument(),nextAudio,0,0);
  }
}
