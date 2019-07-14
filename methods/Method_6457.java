private FileLoadOperation loadFileInternal(final TLRPC.Document document,final SecureDocument secureDocument,final WebFile webDocument,TLRPC.TL_fileLocationToBeDeprecated location,final ImageLocation imageLocation,Object parentObject,final String locationExt,final int locationSize,final int priority,final FileLoadOperationStream stream,final int streamOffset,final int cacheType){
  String fileName=null;
  if (location != null) {
    fileName=getAttachFileName(location,locationExt);
  }
 else   if (secureDocument != null) {
    fileName=getAttachFileName(secureDocument);
  }
 else   if (document != null) {
    fileName=getAttachFileName(document);
  }
 else   if (webDocument != null) {
    fileName=getAttachFileName(webDocument);
  }
  if (fileName == null || fileName.contains("" + Integer.MIN_VALUE)) {
    return null;
  }
  if (cacheType != 10 && !TextUtils.isEmpty(fileName) && !fileName.contains("" + Integer.MIN_VALUE)) {
    loadOperationPathsUI.put(fileName,true);
  }
  FileLoadOperation operation;
  operation=loadOperationPaths.get(fileName);
  if (operation != null) {
    if (cacheType != 10 && operation.isPreloadVideoOperation()) {
      operation.setIsPreloadVideoOperation(false);
    }
    if (stream != null || priority > 0) {
      int datacenterId=operation.getDatacenterId();
      LinkedList<FileLoadOperation> audioLoadOperationQueue=getAudioLoadOperationQueue(datacenterId);
      LinkedList<FileLoadOperation> photoLoadOperationQueue=getPhotoLoadOperationQueue(datacenterId);
      LinkedList<FileLoadOperation> loadOperationQueue=getLoadOperationQueue(datacenterId);
      operation.setForceRequest(true);
      LinkedList<FileLoadOperation> downloadQueue;
      if (MessageObject.isVoiceDocument(document) || MessageObject.isVoiceWebDocument(webDocument)) {
        downloadQueue=audioLoadOperationQueue;
      }
 else       if (secureDocument != null || location != null || MessageObject.isImageWebDocument(webDocument)) {
        downloadQueue=photoLoadOperationQueue;
      }
 else {
        downloadQueue=loadOperationQueue;
      }
      if (downloadQueue != null) {
        int index=downloadQueue.indexOf(operation);
        if (index >= 0) {
          downloadQueue.remove(index);
          if (stream != null) {
            if (downloadQueue == audioLoadOperationQueue) {
              if (operation.start(stream,streamOffset)) {
                currentAudioLoadOperationsCount.put(datacenterId,currentAudioLoadOperationsCount.get(datacenterId) + 1);
              }
            }
 else             if (downloadQueue == photoLoadOperationQueue) {
              if (operation.start(stream,streamOffset)) {
                currentPhotoLoadOperationsCount.put(datacenterId,currentPhotoLoadOperationsCount.get(datacenterId) + 1);
              }
            }
 else {
              if (operation.start(stream,streamOffset)) {
                currentLoadOperationsCount.put(datacenterId,currentLoadOperationsCount.get(datacenterId) + 1);
              }
              if (operation.wasStarted() && !activeFileLoadOperation.contains(operation)) {
                if (stream != null) {
                  pauseCurrentFileLoadOperations(operation);
                }
                activeFileLoadOperation.add(operation);
              }
            }
          }
 else {
            downloadQueue.add(0,operation);
          }
        }
 else {
          if (stream != null) {
            pauseCurrentFileLoadOperations(operation);
          }
          operation.start(stream,streamOffset);
          if (downloadQueue == loadOperationQueue && !activeFileLoadOperation.contains(operation)) {
            activeFileLoadOperation.add(operation);
          }
        }
      }
    }
    return operation;
  }
  File tempDir=getDirectory(MEDIA_DIR_CACHE);
  File storeDir=tempDir;
  int type=MEDIA_DIR_CACHE;
  if (secureDocument != null) {
    operation=new FileLoadOperation(secureDocument);
    type=MEDIA_DIR_DOCUMENT;
  }
 else   if (location != null) {
    operation=new FileLoadOperation(imageLocation,parentObject,locationExt,locationSize);
    type=MEDIA_DIR_IMAGE;
  }
 else   if (document != null) {
    operation=new FileLoadOperation(document,parentObject);
    if (MessageObject.isVoiceDocument(document)) {
      type=MEDIA_DIR_AUDIO;
    }
 else     if (MessageObject.isVideoDocument(document)) {
      type=MEDIA_DIR_VIDEO;
    }
 else {
      type=MEDIA_DIR_DOCUMENT;
    }
  }
 else   if (webDocument != null) {
    operation=new FileLoadOperation(currentAccount,webDocument);
    if (MessageObject.isVoiceWebDocument(webDocument)) {
      type=MEDIA_DIR_AUDIO;
    }
 else     if (MessageObject.isVideoWebDocument(webDocument)) {
      type=MEDIA_DIR_VIDEO;
    }
 else     if (MessageObject.isImageWebDocument(webDocument)) {
      type=MEDIA_DIR_IMAGE;
    }
 else {
      type=MEDIA_DIR_DOCUMENT;
    }
  }
  if (cacheType == 0 || cacheType == 10) {
    storeDir=getDirectory(type);
  }
 else   if (cacheType == 2) {
    operation.setEncryptFile(true);
  }
  operation.setPaths(currentAccount,storeDir,tempDir);
  if (cacheType == 10) {
    operation.setIsPreloadVideoOperation(true);
  }
  final String finalFileName=fileName;
  final int finalType=type;
  FileLoadOperation.FileLoadOperationDelegate fileLoadOperationDelegate=new FileLoadOperation.FileLoadOperationDelegate(){
    @Override public void didFinishLoadingFile(    FileLoadOperation operation,    File finalFile){
      if (!operation.isPreloadVideoOperation() && operation.isPreloadFinished()) {
        return;
      }
      if (!operation.isPreloadVideoOperation()) {
        loadOperationPathsUI.remove(finalFileName);
        if (delegate != null) {
          delegate.fileDidLoaded(finalFileName,finalFile,finalType);
        }
      }
      checkDownloadQueue(operation.getDatacenterId(),document,webDocument,location,finalFileName);
    }
    @Override public void didFailedLoadingFile(    FileLoadOperation operation,    int reason){
      loadOperationPathsUI.remove(finalFileName);
      checkDownloadQueue(operation.getDatacenterId(),document,webDocument,location,finalFileName);
      if (delegate != null) {
        delegate.fileDidFailedLoad(finalFileName,reason);
      }
    }
    @Override public void didChangedLoadProgress(    FileLoadOperation operation,    float progress){
      if (delegate != null) {
        delegate.fileLoadProgressChanged(finalFileName,progress);
      }
    }
  }
;
  operation.setDelegate(fileLoadOperationDelegate);
  int datacenterId=operation.getDatacenterId();
  LinkedList<FileLoadOperation> audioLoadOperationQueue=getAudioLoadOperationQueue(datacenterId);
  LinkedList<FileLoadOperation> photoLoadOperationQueue=getPhotoLoadOperationQueue(datacenterId);
  LinkedList<FileLoadOperation> loadOperationQueue=getLoadOperationQueue(datacenterId);
  loadOperationPaths.put(fileName,operation);
  operation.setPriority(priority);
  if (type == MEDIA_DIR_AUDIO) {
    int maxCount=priority > 0 ? 3 : 1;
    int count=currentAudioLoadOperationsCount.get(datacenterId);
    if (stream != null || count < maxCount) {
      if (operation.start(stream,streamOffset)) {
        currentAudioLoadOperationsCount.put(datacenterId,count + 1);
      }
    }
 else {
      addOperationToQueue(operation,audioLoadOperationQueue);
    }
  }
 else   if (location != null || MessageObject.isImageWebDocument(webDocument)) {
    int maxCount=priority > 0 ? 6 : 2;
    int count=currentPhotoLoadOperationsCount.get(datacenterId);
    if (stream != null || count < maxCount) {
      if (operation.start(stream,streamOffset)) {
        currentPhotoLoadOperationsCount.put(datacenterId,count + 1);
      }
    }
 else {
      addOperationToQueue(operation,photoLoadOperationQueue);
    }
  }
 else {
    int maxCount=priority > 0 ? 4 : 1;
    int count=currentLoadOperationsCount.get(datacenterId);
    if (stream != null || count < maxCount) {
      if (operation.start(stream,streamOffset)) {
        currentLoadOperationsCount.put(datacenterId,count + 1);
        activeFileLoadOperation.add(operation);
      }
      if (operation.wasStarted() && stream != null) {
        pauseCurrentFileLoadOperations(operation);
      }
    }
 else {
      addOperationToQueue(operation,loadOperationQueue);
    }
  }
  return operation;
}
