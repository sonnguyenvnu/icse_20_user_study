private void clearOperaion(RequestInfo currentInfo,boolean preloadChanged){
  int minOffset=Integer.MAX_VALUE;
  for (int a=0; a < requestInfos.size(); a++) {
    RequestInfo info=requestInfos.get(a);
    minOffset=Math.min(info.offset,minOffset);
    if (isPreloadVideoOperation) {
      requestedPreloadedBytesRanges.delete(info.offset);
    }
 else {
      removePart(notRequestedBytesRanges,info.offset,info.offset + currentDownloadChunkSize);
    }
    if (currentInfo == info) {
      continue;
    }
    if (info.requestToken != 0) {
      ConnectionsManager.getInstance(currentAccount).cancelRequest(info.requestToken,true);
    }
  }
  requestInfos.clear();
  for (int a=0; a < delayedRequestInfos.size(); a++) {
    RequestInfo info=delayedRequestInfos.get(a);
    if (isPreloadVideoOperation) {
      requestedPreloadedBytesRanges.delete(info.offset);
    }
 else {
      removePart(notRequestedBytesRanges,info.offset,info.offset + currentDownloadChunkSize);
    }
    if (info.response != null) {
      info.response.disableFree=false;
      info.response.freeResources();
    }
 else     if (info.responseWeb != null) {
      info.responseWeb.disableFree=false;
      info.responseWeb.freeResources();
    }
 else     if (info.responseCdn != null) {
      info.responseCdn.disableFree=false;
      info.responseCdn.freeResources();
    }
    minOffset=Math.min(info.offset,minOffset);
  }
  delayedRequestInfos.clear();
  requestsCount=0;
  if (!preloadChanged && isPreloadVideoOperation) {
    requestedBytesCount=totalPreloadedBytes;
  }
 else   if (notLoadedBytesRanges == null) {
    requestedBytesCount=downloadedBytes=minOffset;
  }
}
