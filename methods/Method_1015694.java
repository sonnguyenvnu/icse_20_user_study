/** 
 * Adds a digest to this digest. For each sender in the other digest, the merge() method will be called.
 */
public MutableDigest merge(Digest digest){
  if (digest == null)   return this;
  for (  Entry entry : digest)   merge(entry.getMember(),entry.getHighestDeliveredSeqno(),entry.getHighestReceivedSeqno());
  return this;
}
