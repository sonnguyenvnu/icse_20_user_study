public boolean append(String string){
  long sequence=0L;
  if (allowDiscard) {
    try {
      sequence=ringBuffer.tryNext();
    }
 catch (    InsufficientCapacityException e) {
      if (isOutDiscardId) {
        if (string != null) {
          SynchronizingSelfLog.warn("discarded selflog ");
        }
      }
      if ((isOutDiscardNumber) && discardCount.incrementAndGet() == discardOutThreshold) {
        discardCount.set(0);
        if (isOutDiscardNumber) {
          SynchronizingSelfLog.warn("discarded " + discardOutThreshold + " selflogs");
        }
      }
      return false;
    }
  }
 else {
    sequence=ringBuffer.next();
  }
  try {
    StringEvent event=ringBuffer.get(sequence);
    event.setString(string);
  }
 catch (  Exception e) {
    SynchronizingSelfLog.error("fail to add event");
    return false;
  }
  ringBuffer.publish(sequence);
  return true;
}
