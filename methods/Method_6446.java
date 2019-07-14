public boolean isLoadingVideo(TLRPC.Document document,boolean player){
  return document != null && loadingVideos.containsKey(getAttachFileName(document) + (player ? "p" : ""));
}
