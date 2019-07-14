/** 
 * Evicts if the map exceeds the maximum capacity. 
 */
private void evict(Node candidate){
  if (data.size() > maximumSize) {
    List<Node> sample=(policy == EvictionPolicy.RANDOM) ? Arrays.asList(table) : sampleStrategy.sample(table,candidate,sampleSize,random,policyStats);
    Node victim=policy.select(sample,random,tick);
    policyStats.recordEviction();
    if (admittor.admit(candidate.key,victim.key)) {
      removeFromTable(victim);
      data.remove(victim.key);
    }
 else {
      removeFromTable(candidate);
      data.remove(candidate.key);
    }
  }
}
