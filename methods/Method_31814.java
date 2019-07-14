/** 
 * Does this time interval overlap the specified time interval. <p> Intervals are inclusive of the start instant and exclusive of the end. An interval overlaps another if it shares some common part of the datetime continuum.  <p> When two intervals are compared the result is one of three states: (a) they abut, (b) there is a gap between them, (c) they overlap. The abuts state takes precedence over the other two, thus a zero duration interval at the start of a larger interval abuts and does not overlap. <p> For example: <pre> [09:00 to 10:00) overlaps [08:00 to 08:30)  = false (completely before) [09:00 to 10:00) overlaps [08:00 to 09:00)  = false (abuts before) [09:00 to 10:00) overlaps [08:00 to 09:30)  = true [09:00 to 10:00) overlaps [08:00 to 10:00)  = true [09:00 to 10:00) overlaps [08:00 to 11:00)  = true [09:00 to 10:00) overlaps [09:00 to 09:00)  = false (abuts before) [09:00 to 10:00) overlaps [09:00 to 09:30)  = true [09:00 to 10:00) overlaps [09:00 to 10:00)  = true [09:00 to 10:00) overlaps [09:00 to 11:00)  = true [09:00 to 10:00) overlaps [09:30 to 09:30)  = true [09:00 to 10:00) overlaps [09:30 to 10:00)  = true [09:00 to 10:00) overlaps [09:30 to 11:00)  = true [09:00 to 10:00) overlaps [10:00 to 10:00)  = false (abuts after) [09:00 to 10:00) overlaps [10:00 to 11:00)  = false (abuts after) [09:00 to 10:00) overlaps [10:30 to 11:00)  = false (completely after) [14:00 to 14:00) overlaps [14:00 to 14:00)  = false (abuts before and after) [14:00 to 14:00) overlaps [13:00 to 15:00)  = true </pre>
 * @param interval  the time interval to compare to, null means a zero length interval now
 * @return true if the time intervals overlap
 */
public boolean overlaps(ReadableInterval interval){
  long thisStart=getStartMillis();
  long thisEnd=getEndMillis();
  if (interval == null) {
    long now=DateTimeUtils.currentTimeMillis();
    return (thisStart < now && now < thisEnd);
  }
 else {
    long otherStart=interval.getStartMillis();
    long otherEnd=interval.getEndMillis();
    return (thisStart < otherEnd && otherStart < thisEnd);
  }
}
