/** 
 * Returns a copy of this time with the minute of hour field updated. <p> TimeOfDay is immutable, so there are no set methods. Instead, this method returns a new instance with the value of minute of hour changed.
 * @param minute  the minute of hour to set
 * @return a copy of this object with the field set
 * @throws IllegalArgumentException if the value is invalid
 * @since 1.3
 */
public TimeOfDay withMinuteOfHour(int minute){
  int[] newValues=getValues();
  newValues=getChronology().minuteOfHour().set(this,MINUTE_OF_HOUR,newValues,minute);
  return new TimeOfDay(this,newValues);
}
