/** 
 * Moves the entry to the next higher frequency list, creating it if necessary. 
 */
private void onHit(Node node){
  policyStats.recordHit();
  int newCount=node.freq.count + 1;
  FrequencyNode freqN=(node.freq.next.count == newCount) ? node.freq.next : new FrequencyNode(newCount,node.freq);
  node.remove();
  if (node.freq.isEmpty()) {
    node.freq.remove();
  }
  node.freq=freqN;
  node.append();
}
