private void removeLoadingVideoInternal(TLRPC.Document document,boolean player){
  String key=getAttachFileName(document);
  String dKey=key + (player ? "p" : "");
  if (loadingVideos.remove(dKey) != null) {
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.videoLoadingStateChanged,key);
  }
}
