/** 
 * ?????????????
 * @param intervals
 * @return
 */
public List<Intervalable> removeOverlaps(List<Intervalable> intervals){
  Collections.sort(intervals,new IntervalableComparatorBySize());
  Set<Intervalable> removeIntervals=new TreeSet<Intervalable>();
  for (  Intervalable interval : intervals) {
    if (removeIntervals.contains(interval)) {
      continue;
    }
    removeIntervals.addAll(findOverlaps(interval));
  }
  for (  Intervalable removeInterval : removeIntervals) {
    intervals.remove(removeInterval);
  }
  Collections.sort(intervals,new IntervalableComparatorByPosition());
  return intervals;
}
