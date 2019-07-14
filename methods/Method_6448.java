public void onNetworkChanged(final boolean slow){
  fileLoaderQueue.postRunnable(() -> {
    for (    ConcurrentHashMap.Entry<String,FileUploadOperation> entry : uploadOperationPaths.entrySet()) {
      entry.getValue().onNetworkChanged(slow);
    }
    for (    ConcurrentHashMap.Entry<String,FileUploadOperation> entry : uploadOperationPathsEnc.entrySet()) {
      entry.getValue().onNetworkChanged(slow);
    }
  }
);
}
