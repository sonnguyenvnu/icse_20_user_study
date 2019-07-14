/** 
 * Gets the gap between this interval and another interval. The other interval can be either before or after this interval. <p> Intervals are inclusive of the start instant and exclusive of the end. An interval has a gap to another interval if there is a non-zero duration between them. This method returns the amount of the gap only if the intervals do actually have a gap between them. If the intervals overlap or abut, then null is returned. <p> When two intervals are compared the result is one of three states: (a) they abut, (b) there is a gap between them, (c) they overlap. The abuts state takes precedence over the other two, thus a zero duration interval at the start of a larger interval abuts and does not overlap. <p> The chronology of the returned interval is the same as that of this interval (the chronology of the interval parameter is not used). Note that the use of the chronology was only correctly implemented in version 1.3.
 * @param interval  the interval to examine, null means now
 * @return the gap interval, null if no gap
 * @since 1.1
 */
public Interval gap(ReadableInterval interval){
  interval=DateTimeUtils.getReadableInterval(interval);
  long otherStart=interval.getStartMillis();
  long otherEnd=interval.getEndMillis();
  long thisStart=getStartMillis();
  long thisEnd=getEndMillis();
  if (thisStart > otherEnd) {
    return new Interval(otherEnd,thisStart,getChronology());
  }
 else   if (otherStart > thisEnd) {
    return new Interval(thisEnd,otherStart,getChronology());
  }
 else {
    return null;
  }
}
