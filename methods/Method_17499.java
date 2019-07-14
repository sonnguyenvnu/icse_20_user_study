/** 
 * Returns the next victim, excluding the newly added candidate. This exclusion is required so that a candidate has a fair chance to be used, rather than always rejected due to existing entries having a high frequency from the distant past.
 */
Node nextVictim(Node candidate){
  if (policy == EvictionPolicy.MFU) {
    return freq0.prev.nextNode.next;
  }
  Node victim=freq0.next.nextNode.next;
  if (victim == candidate) {
    victim=(victim.next == victim.prev) ? victim.freq.next.nextNode.next : victim.next;
  }
  return victim;
}
