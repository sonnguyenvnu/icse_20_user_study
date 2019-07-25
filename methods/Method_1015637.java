public synchronized void reset(Collection<Address> members){
  suspected_mbrs.clear();
  missing_acks.clear();
  addAll(members);
  all_acks_received.reset();
}
