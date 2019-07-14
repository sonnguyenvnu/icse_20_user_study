/** 
 * Does this time interval contain the specified time interval. <p> Non-zero duration intervals are inclusive of the start instant and exclusive of the end. The other interval is contained if this interval wholly contains, starts, finishes or equals it. A zero duration interval cannot contain anything. <p> When two intervals are compared the result is one of three states: (a) they abut, (b) there is a gap between them, (c) they overlap. The <code>contains</code> method is not related to these states. In particular, a zero duration interval is contained at the start of a larger interval, but does not overlap (it abuts instead). <p> For example: <pre> [09:00 to 10:00) contains [09:00 to 10:00)  = true [09:00 to 10:00) contains [09:00 to 09:30)  = true [09:00 to 10:00) contains [09:30 to 10:00)  = true [09:00 to 10:00) contains [09:15 to 09:45)  = true [09:00 to 10:00) contains [09:00 to 09:00)  = true [09:00 to 10:00) contains [08:59 to 10:00)  = false (otherStart before thisStart) [09:00 to 10:00) contains [09:00 to 10:01)  = false (otherEnd after thisEnd) [09:00 to 10:00) contains [10:00 to 10:00)  = false (otherStart equals thisEnd) [14:00 to 14:00) contains [14:00 to 14:00)  = false (zero duration contains nothing) </pre> Passing in a <code>null</code> parameter will have the same effect as calling  {@link #containsNow()}.
 * @param interval  the time interval to compare to, null means a zero duration interval now
 * @return true if this time interval contains the time interval
 */
public boolean contains(ReadableInterval interval){
  if (interval == null) {
    return containsNow();
  }
  long otherStart=interval.getStartMillis();
  long otherEnd=interval.getEndMillis();
  long thisStart=getStartMillis();
  long thisEnd=getEndMillis();
  return (thisStart <= otherStart && otherStart < thisEnd && otherEnd <= thisEnd);
}
