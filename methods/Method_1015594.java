public void send(Message msg) throws Exception {
  num_senders.incrementAndGet();
  long size=msg.size();
  lock.lock();
  try {
    if (count + size >= transport.getMaxBundleSize())     sendBundledMessages();
    addMessage(msg,size);
    if (num_senders.decrementAndGet() == 0)     sendBundledMessages();
  }
  finally {
    lock.unlock();
  }
}
