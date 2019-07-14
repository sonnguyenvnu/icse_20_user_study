public List<Interval> insert(List<Interval> intervals,Interval newInterval){
  if (intervals.isEmpty())   return Collections.singletonList(newInterval);
  List<Interval> ans=new ArrayList<>();
  int i=0, len=intervals.size();
  for (; i < len; ++i) {
    Interval interval=intervals.get(i);
    if (interval.end < newInterval.start)     ans.add(interval);
 else     break;
  }
  for (; i < len; ++i) {
    Interval interval=intervals.get(i);
    if (interval.start <= newInterval.end) {
      newInterval.start=Math.min(newInterval.start,interval.start);
      newInterval.end=Math.max(newInterval.end,interval.end);
    }
 else     break;
  }
  ans.add(newInterval);
  for (; i < len; ++i) {
    ans.add(intervals.get(i));
  }
  return ans;
}
