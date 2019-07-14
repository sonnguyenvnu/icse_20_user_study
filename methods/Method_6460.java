private void checkDownloadQueue(final int datacenterId,final TLRPC.Document document,final WebFile webDocument,final TLRPC.FileLocation location,final String arg1){
  fileLoaderQueue.postRunnable(() -> {
    LinkedList<FileLoadOperation> audioLoadOperationQueue=getAudioLoadOperationQueue(datacenterId);
    LinkedList<FileLoadOperation> photoLoadOperationQueue=getPhotoLoadOperationQueue(datacenterId);
    LinkedList<FileLoadOperation> loadOperationQueue=getLoadOperationQueue(datacenterId);
    FileLoadOperation operation=loadOperationPaths.remove(arg1);
    if (MessageObject.isVoiceDocument(document) || MessageObject.isVoiceWebDocument(webDocument)) {
      int count=currentAudioLoadOperationsCount.get(datacenterId);
      if (operation != null) {
        if (operation.wasStarted()) {
          count--;
          currentAudioLoadOperationsCount.put(datacenterId,count);
        }
 else {
          audioLoadOperationQueue.remove(operation);
        }
      }
      while (!audioLoadOperationQueue.isEmpty()) {
        operation=audioLoadOperationQueue.get(0);
        int maxCount=operation.getPriority() != 0 ? 3 : 1;
        if (count < maxCount) {
          operation=audioLoadOperationQueue.poll();
          if (operation != null && operation.start()) {
            count++;
            currentAudioLoadOperationsCount.put(datacenterId,count);
          }
        }
 else {
          break;
        }
      }
    }
 else     if (location != null || MessageObject.isImageWebDocument(webDocument)) {
      int count=currentPhotoLoadOperationsCount.get(datacenterId);
      if (operation != null) {
        if (operation.wasStarted()) {
          count--;
          currentPhotoLoadOperationsCount.put(datacenterId,count);
        }
 else {
          photoLoadOperationQueue.remove(operation);
        }
      }
      while (!photoLoadOperationQueue.isEmpty()) {
        operation=photoLoadOperationQueue.get(0);
        int maxCount=operation.getPriority() != 0 ? 6 : 2;
        if (count < maxCount) {
          operation=photoLoadOperationQueue.poll();
          if (operation != null && operation.start()) {
            count++;
            currentPhotoLoadOperationsCount.put(datacenterId,count);
          }
        }
 else {
          break;
        }
      }
    }
 else {
      int count=currentLoadOperationsCount.get(datacenterId);
      if (operation != null) {
        if (operation.wasStarted()) {
          count--;
          currentLoadOperationsCount.put(datacenterId,count);
        }
 else {
          loadOperationQueue.remove(operation);
        }
        activeFileLoadOperation.remove(operation);
      }
      while (!loadOperationQueue.isEmpty()) {
        operation=loadOperationQueue.get(0);
        int maxCount=operation.isForceRequest() ? 3 : 1;
        if (count < maxCount) {
          operation=loadOperationQueue.poll();
          if (operation != null && operation.start()) {
            count++;
            currentLoadOperationsCount.put(datacenterId,count);
            if (!activeFileLoadOperation.contains(operation)) {
              activeFileLoadOperation.add(operation);
            }
          }
        }
 else {
          break;
        }
      }
    }
  }
);
}
