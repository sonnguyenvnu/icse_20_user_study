public MutableDigest set(Address member,long highest_delivered_seqno,long highest_received_seqno){
  if (member == null)   return this;
  int index=find(member);
  if (index >= 0) {
    seqnos[index * 2]=highest_delivered_seqno;
    seqnos[index * 2 + 1]=highest_received_seqno;
  }
  return this;
}
