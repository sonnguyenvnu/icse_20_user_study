private void checkIsNextVoiceFileDownloaded(int currentAccount){
  if (voiceMessagesPlaylist == null || voiceMessagesPlaylist.size() < 2) {
    return;
  }
  MessageObject nextAudio=voiceMessagesPlaylist.get(1);
  File file=null;
  if (nextAudio.messageOwner.attachPath != null && nextAudio.messageOwner.attachPath.length() > 0) {
    file=new File(nextAudio.messageOwner.attachPath);
    if (!file.exists()) {
      file=null;
    }
  }
  final File cacheFile=file != null ? file : FileLoader.getPathToMessage(nextAudio.messageOwner);
  boolean exist=cacheFile != null && cacheFile.exists();
  if (cacheFile != null && cacheFile != file && !cacheFile.exists()) {
    FileLoader.getInstance(currentAccount).loadFile(nextAudio.getDocument(),nextAudio,0,0);
  }
}
