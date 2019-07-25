protected void update(Address sender){
  if (sender != null && !sender.equals(local_addr))   timestamps.put(sender,getTimestamp());
  if (log.isTraceEnabled())   log.trace("Received heartbeat from %s",sender);
}
