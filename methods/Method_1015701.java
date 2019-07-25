protected void process(){
  if (consumer_lock.tryLock()) {
    try {
      while (true) {
        T element=queue.poll();
        if (element != null && handler != null) {
          try {
            handler.handle(element);
          }
 catch (          Throwable t) {
            t.printStackTrace(System.err);
          }
        }
        producer_lock.lock();
        try {
          if (count == 0 || count - 1 == 0) {
            count=0;
            consumer_lock.unlock();
            return;
          }
          count--;
        }
  finally {
          producer_lock.unlock();
        }
      }
    }
  finally {
      if (consumer_lock.isHeldByCurrentThread())       consumer_lock.unlock();
    }
  }
}
