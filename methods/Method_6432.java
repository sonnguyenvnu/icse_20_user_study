private void processLaterArrays(){
  for (  HashMap.Entry<String,FileDownloadProgressListener> listener : addLaterArray.entrySet()) {
    addLoadingFileObserver(listener.getKey(),listener.getValue());
  }
  addLaterArray.clear();
  for (  FileDownloadProgressListener listener : deleteLaterArray) {
    removeLoadingFileObserver(listener);
  }
  deleteLaterArray.clear();
}
