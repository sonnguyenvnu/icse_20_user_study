/** 
 * ????
 * @param intervals ????
 * @return ????
 */
public int determineMedian(List<Intervalable> intervals){
  int start=-1;
  int end=-1;
  for (  Intervalable interval : intervals) {
    int currentStart=interval.getStart();
    int currentEnd=interval.getEnd();
    if (start == -1 || currentStart < start) {
      start=currentStart;
    }
    if (end == -1 || currentEnd > end) {
      end=currentEnd;
    }
  }
  return (start + end) / 2;
}
