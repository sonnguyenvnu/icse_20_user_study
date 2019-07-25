void drain(){
  while (!pendingRequests.isEmpty()) {
    final int currentActiveRequests=numActiveRequests.get();
    if (currentActiveRequests >= maxConcurrency) {
      break;
    }
    if (numActiveRequests.compareAndSet(currentActiveRequests,currentActiveRequests + 1)) {
      final PendingTask task=pendingRequests.poll();
      if (task == null) {
        numActiveRequests.decrementAndGet();
        if (!pendingRequests.isEmpty()) {
          continue;
        }
 else {
          break;
        }
      }
      task.run();
    }
  }
}
