/** 
 * Returns the interval that represents the range of the minimum and maximum values of this field. <p> For example, <code>datetime.monthOfYear().toInterval()</code> will return an interval over the whole month.
 * @return the interval of this field
 * @since 1.2
 */
public Interval toInterval(){
  DateTimeField field=getField();
  long start=field.roundFloor(getMillis());
  long end=field.add(start,1);
  Interval interval=new Interval(start,end,getChronology());
  return interval;
}
