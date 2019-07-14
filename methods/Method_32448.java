/** 
 * Does this interval abut with the interval specified. <p> Intervals are inclusive of the start instant and exclusive of the end. An interval abuts if it starts immediately after, or ends immediately before this interval without overlap. A zero duration interval abuts with itself. <p> When two intervals are compared the result is one of three states: (a) they abut, (b) there is a gap between them, (c) they overlap. The abuts state takes precedence over the other two, thus a zero duration interval at the start of a larger interval abuts and does not overlap. <p> For example: <pre> [09:00 to 10:00) abuts [08:00 to 08:30)  = false (completely before) [09:00 to 10:00) abuts [08:00 to 09:00)  = true [09:00 to 10:00) abuts [08:00 to 09:01)  = false (overlaps) [09:00 to 10:00) abuts [09:00 to 09:00)  = true [09:00 to 10:00) abuts [09:00 to 09:01)  = false (overlaps) [09:00 to 10:00) abuts [10:00 to 10:00)  = true [09:00 to 10:00) abuts [10:00 to 10:30)  = true [09:00 to 10:00) abuts [10:30 to 11:00)  = false (completely after) [14:00 to 14:00) abuts [14:00 to 14:00)  = true [14:00 to 14:00) abuts [14:00 to 15:00)  = true [14:00 to 14:00) abuts [13:00 to 14:00)  = true </pre>
 * @param interval  the interval to examine, null means now
 * @return true if the interval abuts
 * @since 1.1
 */
public boolean abuts(ReadableInterval interval){
  if (interval == null) {
    long now=DateTimeUtils.currentTimeMillis();
    return (getStartMillis() == now || getEndMillis() == now);
  }
 else {
    return (interval.getEndMillis() == getStartMillis() || getEndMillis() == interval.getStartMillis());
  }
}
