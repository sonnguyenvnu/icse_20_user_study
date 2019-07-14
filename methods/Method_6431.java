public void addLoadingFileObserver(String fileName,MessageObject messageObject,FileDownloadProgressListener observer){
  if (listenerInProgress) {
    addLaterArray.put(fileName,observer);
    return;
  }
  removeLoadingFileObserver(observer);
  ArrayList<WeakReference<FileDownloadProgressListener>> arrayList=loadingFileObservers.get(fileName);
  if (arrayList == null) {
    arrayList=new ArrayList<>();
    loadingFileObservers.put(fileName,arrayList);
  }
  arrayList.add(new WeakReference<>(observer));
  if (messageObject != null) {
    ArrayList<MessageObject> messageObjects=loadingFileMessagesObservers.get(fileName);
    if (messageObjects == null) {
      messageObjects=new ArrayList<>();
      loadingFileMessagesObservers.put(fileName,messageObjects);
    }
    messageObjects.add(messageObject);
  }
  observersByTag.put(observer.getObserverTag(),fileName);
}
