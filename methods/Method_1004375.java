void append(final List<AckEntry> batch){
  if (batch == null || batch.isEmpty())   return;
  updateLock.lock();
  try {
    if (lastAppendOffset != -1 && lastAppendOffset + 1 != batch.get(0).pullOffset()) {
      LOGGER.warn("{}/{} append ack entry not continous. last: {}, new: {}",subject,group,lastAppendOffset,batch.get(0).pullOffset());
      appendErrorCount.inc();
    }
    if (head == null) {
      beginScanPosition=head=batch.get(0);
      minPullOffset.set(head.pullOffset());
    }
    if (tail != null) {
      tail.setNext(batch.get(0));
    }
    tail=batch.get(batch.size() - 1);
    lastAppendOffset=tail.pullOffset();
    maxPullOffset.set(tail.pullOffset());
    toSendNum.getAndAdd(batch.size());
  }
  finally {
    updateLock.unlock();
  }
}
