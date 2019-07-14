/** 
 * Returns a copy of this time with the second of minute field updated. <p> TimeOfDay is immutable, so there are no set methods. Instead, this method returns a new instance with the value of second of minute changed.
 * @param second  the second of minute to set
 * @return a copy of this object with the field set
 * @throws IllegalArgumentException if the value is invalid
 * @since 1.3
 */
public TimeOfDay withSecondOfMinute(int second){
  int[] newValues=getValues();
  newValues=getChronology().secondOfMinute().set(this,SECOND_OF_MINUTE,newValues,second);
  return new TimeOfDay(this,newValues);
}
