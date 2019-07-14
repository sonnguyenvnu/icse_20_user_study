private void requestFileOffsets(int offset){
  if (requestingCdnOffsets) {
    return;
  }
  requestingCdnOffsets=true;
  TLRPC.TL_upload_getCdnFileHashes req=new TLRPC.TL_upload_getCdnFileHashes();
  req.file_token=cdnToken;
  req.offset=offset;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error != null) {
      onFail(false,0);
    }
 else {
      requestingCdnOffsets=false;
      TLRPC.Vector vector=(TLRPC.Vector)response;
      if (!vector.objects.isEmpty()) {
        if (cdnHashes == null) {
          cdnHashes=new SparseArray<>();
        }
        for (int a=0; a < vector.objects.size(); a++) {
          TLRPC.TL_fileHash hash=(TLRPC.TL_fileHash)vector.objects.get(a);
          cdnHashes.put(hash.offset,hash);
        }
      }
      for (int a=0; a < delayedRequestInfos.size(); a++) {
        RequestInfo delayedRequestInfo=delayedRequestInfos.get(a);
        if (notLoadedBytesRanges != null || downloadedBytes == delayedRequestInfo.offset) {
          delayedRequestInfos.remove(a);
          if (!processRequestResult(delayedRequestInfo,null)) {
            if (delayedRequestInfo.response != null) {
              delayedRequestInfo.response.disableFree=false;
              delayedRequestInfo.response.freeResources();
            }
 else             if (delayedRequestInfo.responseWeb != null) {
              delayedRequestInfo.responseWeb.disableFree=false;
              delayedRequestInfo.responseWeb.freeResources();
            }
 else             if (delayedRequestInfo.responseCdn != null) {
              delayedRequestInfo.responseCdn.disableFree=false;
              delayedRequestInfo.responseCdn.freeResources();
            }
          }
          break;
        }
      }
    }
  }
,null,null,0,datacenterId,ConnectionsManager.ConnectionTypeGeneric,true);
}
