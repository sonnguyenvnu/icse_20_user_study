/** 
 * We're guaranteed that only one thread will be called with this method at any time 
 */
protected void process(Collection<R> requests){
  for (; ; ) {
    while (!requests.isEmpty()) {
      removeAndProcess(requests);
    }
    lock.lock();
    try {
      if (requests.isEmpty()) {
        setProcessing(false);
        return;
      }
    }
  finally {
      lock.unlock();
    }
  }
}
