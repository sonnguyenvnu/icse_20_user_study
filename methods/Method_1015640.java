public synchronized void ack(Address member){
  if (member != null && missing_acks.remove(member) && missing_acks.isEmpty())   all_acks_received.setResult(Boolean.TRUE);
}
