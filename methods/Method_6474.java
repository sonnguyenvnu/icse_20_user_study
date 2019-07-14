protected void removeStreamListener(final FileStreamLoadOperation operation){
  Utilities.stageQueue.postRunnable(() -> {
    if (streamListeners == null) {
      return;
    }
    streamListeners.remove(operation);
  }
);
}
