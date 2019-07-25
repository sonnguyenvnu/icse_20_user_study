public synchronized void ack(Address... members){
  for (  Address member : members) {
    if (member != null && missing_acks.remove(member) && missing_acks.isEmpty())     all_acks_received.setResult(Boolean.TRUE);
  }
}
