/** 
 * Returns the highest delivered and received seqnos associated with a member.
 * @param member
 * @return An array of 2 elements: highest_delivered and highest_received seqnos
 */
public long[] get(Address member){
  int index=find(member);
  if (index < 0)   return null;
  return new long[]{seqnos[index * 2],seqnos[index * 2 + 1]};
}
