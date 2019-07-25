/** 
 * Similar to set(), but if the sender already exists, its seqnos will be modified (no new entry) as follows: <ol> <li>this.highest_delivered_seqno=max(this.highest_delivered_seqno, highest_delivered_seqno) <li>this.highest_received_seqno=max(this.highest_received_seqno, highest_received_seqno) </ol>
 */
public MutableDigest merge(final Address member,final long highest_delivered_seqno,final long highest_received_seqno){
  if (member == null)   return this;
  long[] entry=get(member);
  long hd=entry == null ? highest_delivered_seqno : Math.max(entry[0],highest_delivered_seqno);
  long hr=entry == null ? highest_received_seqno : Math.max(entry[1],highest_received_seqno);
  return set(member,hd,hr);
}
