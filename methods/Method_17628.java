/** 
 * Evicts if the map exceeds the maximum capacity. 
 */
private void evict(){
  if (windowSize <= (window.length - 1)) {
    return;
  }
  Node candidate=window[random.nextInt(window.length)];
  removeFromTable(window,candidate);
  windowSize--;
  main[mainSize]=candidate;
  candidate.index=mainSize;
  mainSize++;
  if (data.size() > maximumSize) {
    Node victim=main[random.nextInt(main.length)];
    Node evict=admittor.admit(candidate.key,victim.key) ? victim : candidate;
    removeFromTable(main,evict);
    data.remove(evict.key);
    mainSize--;
    policyStats.recordEviction();
  }
}
