@Override public void push(RowMap r) throws Exception {
  if (r instanceof HeartbeatRowMap) {
    this.context.setPosition(r);
  }
  try {
    this.queue.put(r);
  }
 catch (  InterruptedException e) {
  }
}
