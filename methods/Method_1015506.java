protected void update(Address sender){
  if (sender != null && !sender.equals(local_addr)) {
    AtomicBoolean heartbeat_received=timestamps.get(sender);
    if (heartbeat_received != null)     heartbeat_received.compareAndSet(false,true);
 else     timestamps.putIfAbsent(sender,new AtomicBoolean(true));
  }
  if (log.isTraceEnabled())   log.trace("Received heartbeat from %s",sender);
}
