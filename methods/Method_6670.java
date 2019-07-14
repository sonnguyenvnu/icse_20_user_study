protected void checkIsNextMediaFileDownloaded(){
  if (playingMessageObject == null || !playingMessageObject.isMusic()) {
    return;
  }
  checkIsNextMusicFileDownloaded(playingMessageObject.currentAccount);
}
