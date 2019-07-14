/** 
 * ??IntervalNode.findOverlaps(Intervalable)????????NPE
 * @see com.hankcs.hanlp.algorithm.ahocorasick.interval.IntervalNode#findOverlaps(Intervalable)
 * @param node
 * @param interval
 * @return
 */
protected static List<Intervalable> findOverlappingRanges(IntervalNode node,Intervalable interval){
  if (node != null) {
    return node.findOverlaps(interval);
  }
  return Collections.emptyList();
}
