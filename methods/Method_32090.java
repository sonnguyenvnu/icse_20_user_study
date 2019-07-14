/** 
 * Converts this object to an <code>Interval</code> encompassing the whole of this day. <p> The interval starts at midnight 00:00 and ends at 00:00 the following day, (which is not included in the interval, as intervals are half-open).
 * @return an interval over the day
 */
public Interval toInterval(){
  Chronology chrono=getChronology();
  long start=getMillis();
  long end=DurationFieldType.days().getField(chrono).add(start,1);
  return new Interval(start,end,chrono);
}
