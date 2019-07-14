private void loadFile(final TLRPC.Document document,final SecureDocument secureDocument,final WebFile webDocument,TLRPC.TL_fileLocationToBeDeprecated location,final ImageLocation imageLocation,final Object parentObject,final String locationExt,final int locationSize,final int priority,final int cacheType){
  String fileName;
  if (location != null) {
    fileName=getAttachFileName(location,locationExt);
  }
 else   if (document != null) {
    fileName=getAttachFileName(document);
  }
 else   if (webDocument != null) {
    fileName=getAttachFileName(webDocument);
  }
 else {
    fileName=null;
  }
  if (cacheType != 10 && !TextUtils.isEmpty(fileName) && !fileName.contains("" + Integer.MIN_VALUE)) {
    loadOperationPathsUI.put(fileName,true);
  }
  fileLoaderQueue.postRunnable(() -> loadFileInternal(document,secureDocument,webDocument,location,imageLocation,parentObject,locationExt,locationSize,priority,null,0,cacheType));
}
