private void cancelLoadFile(final TLRPC.Document document,final SecureDocument secureDocument,final WebFile webDocument,final TLRPC.FileLocation location,final String locationExt){
  if (location == null && document == null && webDocument == null && secureDocument == null) {
    return;
  }
  final String fileName;
  if (location != null) {
    fileName=getAttachFileName(location,locationExt);
  }
 else   if (document != null) {
    fileName=getAttachFileName(document);
  }
 else   if (secureDocument != null) {
    fileName=getAttachFileName(secureDocument);
  }
 else   if (webDocument != null) {
    fileName=getAttachFileName(webDocument);
  }
 else {
    fileName=null;
  }
  if (fileName == null) {
    return;
  }
  loadOperationPathsUI.remove(fileName);
  fileLoaderQueue.postRunnable(() -> {
    FileLoadOperation operation=loadOperationPaths.remove(fileName);
    if (operation != null) {
      int datacenterId=operation.getDatacenterId();
      if (MessageObject.isVoiceDocument(document) || MessageObject.isVoiceWebDocument(webDocument)) {
        LinkedList<FileLoadOperation> audioLoadOperationQueue=getAudioLoadOperationQueue(datacenterId);
        if (!audioLoadOperationQueue.remove(operation)) {
          currentAudioLoadOperationsCount.put(datacenterId,currentAudioLoadOperationsCount.get(datacenterId) - 1);
        }
      }
 else       if (secureDocument != null || location != null || MessageObject.isImageWebDocument(webDocument)) {
        LinkedList<FileLoadOperation> photoLoadOperationQueue=getPhotoLoadOperationQueue(datacenterId);
        if (!photoLoadOperationQueue.remove(operation)) {
          currentPhotoLoadOperationsCount.put(datacenterId,currentPhotoLoadOperationsCount.get(datacenterId) - 1);
        }
      }
 else {
        LinkedList<FileLoadOperation> loadOperationQueue=getLoadOperationQueue(datacenterId);
        if (!loadOperationQueue.remove(operation)) {
          currentLoadOperationsCount.put(datacenterId,currentLoadOperationsCount.get(datacenterId) - 1);
        }
        activeFileLoadOperation.remove(operation);
      }
      operation.cancel();
    }
  }
);
}
