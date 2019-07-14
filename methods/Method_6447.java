public void checkUploadNewDataAvailable(final String location,final boolean encrypted,final long newAvailableSize,final long finalSize){
  fileLoaderQueue.postRunnable(() -> {
    FileUploadOperation operation;
    if (encrypted) {
      operation=uploadOperationPathsEnc.get(location);
    }
 else {
      operation=uploadOperationPaths.get(location);
    }
    if (operation != null) {
      operation.checkNewDataAvailable(newAvailableSize,finalSize);
    }
 else     if (finalSize != 0) {
      uploadSizes.put(location,finalSize);
    }
  }
);
}
