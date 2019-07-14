/** 
 * Starting from the oldest interval, this function tries to slide the intervals backwards into the past, so that gaps are eliminated and streaks are maximized. When it detects that sliding an interval would not help fixing any gap, it leaves the interval unchanged.
 */
static void snapIntervalsTogether(@NonNull ArrayList<Interval> intervals){
  for (int i=1; i < intervals.size(); i++) {
    Interval curr=intervals.get(i);
    Interval prev=intervals.get(i - 1);
    int gap=prev.end.daysUntil(curr.begin) - 1;
    if (gap <= 0 || curr.end.minus(gap).isOlderThan(curr.center))     continue;
    intervals.set(i,new Interval(curr.begin.minus(gap),curr.center,curr.end.minus(gap)));
  }
}
