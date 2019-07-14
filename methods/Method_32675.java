/** 
 * Returns a copy of this time with the value of the specified field increased, wrapping to what would be a new day if required. <p> If the addition is zero, then <code>this</code> is returned. <p> These three lines are equivalent: <pre> TimeOfDay added = tod.withFieldAdded(DurationFieldType.minutes(), 6); TimeOfDay added = tod.plusMinutes(6); TimeOfDay added = tod.minuteOfHour().addToCopy(6); </pre>
 * @param fieldType  the field type to add to, not null
 * @param amount  the amount to add
 * @return a copy of this instance with the field updated
 * @throws IllegalArgumentException if the value is null or invalid
 * @throws ArithmeticException if the new datetime exceeds the capacity
 */
public TimeOfDay withFieldAdded(DurationFieldType fieldType,int amount){
  int index=indexOfSupported(fieldType);
  if (amount == 0) {
    return this;
  }
  int[] newValues=getValues();
  newValues=getField(index).addWrapPartial(this,index,newValues,amount);
  return new TimeOfDay(this,newValues);
}
