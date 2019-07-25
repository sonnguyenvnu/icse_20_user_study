public MutableDigest set(Digest digest){
  if (digest == null)   return this;
  for (  Entry entry : digest)   set(entry.getMember(),entry.getHighestDeliveredSeqno(),entry.getHighestReceivedSeqno());
  return this;
}
