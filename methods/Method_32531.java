/** 
 * Returns a copy of this month-day with the value of the specified field increased. <p> If the addition is zero, then <code>this</code> is returned. <p> These three lines are equivalent: <pre> MonthDay added = md.withFieldAdded(DurationFieldType.days(), 6); MonthDay added = md.plusDays(6); MonthDay added = md.dayOfMonth().addToCopy(6); </pre>
 * @param fieldType  the field type to add to, not null
 * @param amount  the amount to add
 * @return a copy of this instance with the field updated, never null
 * @throws IllegalArgumentException if the value is null or invalid
 * @throws ArithmeticException if the new date-time exceeds the capacity
 */
public MonthDay withFieldAdded(DurationFieldType fieldType,int amount){
  int index=indexOfSupported(fieldType);
  if (amount == 0) {
    return this;
  }
  int[] newValues=getValues();
  newValues=getField(index).add(this,index,newValues,amount);
  return new MonthDay(this,newValues);
}
