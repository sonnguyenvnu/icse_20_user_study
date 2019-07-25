@Override public void ack(Object msgId){
  inTransitQueue.remove(msgId);
  retries.remove(msgId);
}
