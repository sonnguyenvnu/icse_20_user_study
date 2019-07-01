@Override public CompletableFuture<List<Entry.Reader>> _XXXXX_(int numEntries){
  final PendingReadRequest readRequest=new PendingReadRequest(numEntries);
  if (checkClosedOrInError()) {
    readRequest.completeExceptionally(lastExceptionUpdater.get(this));
  }
 else {
    boolean wasQueueEmpty;
synchronized (readQueue) {
      wasQueueEmpty=readQueue.isEmpty();
      readQueue.add(readRequest);
    }
    if (wasQueueEmpty) {
      processReadRequests();
    }
  }
  return readRequest.getPromise();
}