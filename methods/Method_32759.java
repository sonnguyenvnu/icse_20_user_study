/** 
 * Returns a copy of this date with the value of the specified field increased. <p> If the addition is zero, then <code>this</code> is returned. <p> These three lines are equivalent: <pre> YearMonthDay added = ymd.withFieldAdded(DurationFieldType.days(), 6); YearMonthDay added = ymd.plusDays(6); YearMonthDay added = ymd.dayOfMonth().addToCopy(6); </pre>
 * @param fieldType  the field type to add to, not null
 * @param amount  the amount to add
 * @return a copy of this instance with the field updated
 * @throws IllegalArgumentException if the value is null or invalid
 * @throws ArithmeticException if the new datetime exceeds the capacity
 */
public YearMonthDay withFieldAdded(DurationFieldType fieldType,int amount){
  int index=indexOfSupported(fieldType);
  if (amount == 0) {
    return this;
  }
  int[] newValues=getValues();
  newValues=getField(index).add(this,index,newValues,amount);
  return new YearMonthDay(this,newValues);
}
