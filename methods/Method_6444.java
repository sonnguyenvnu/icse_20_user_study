public void setLoadingVideoForPlayer(TLRPC.Document document,boolean player){
  if (document == null) {
    return;
  }
  String key=getAttachFileName(document);
  if (loadingVideos.containsKey(key + (player ? "" : "p"))) {
    loadingVideos.put(key + (player ? "p" : ""),true);
  }
}
