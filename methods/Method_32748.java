/** 
 * Returns a copy of this year-month with the value of the specified field increased. <p> If the addition is zero, then <code>this</code> is returned. <p> These three lines are equivalent: <pre> YearMonth added = ym.withFieldAdded(DurationFieldType.months(), 6); YearMonth added = ym.plusMonths(6); YearMonth added = ym.monthOfYear().addToCopy(6); </pre>
 * @param fieldType  the field type to add to, not null
 * @param amount  the amount to add
 * @return a copy of this instance with the field updated, never null
 * @throws IllegalArgumentException if the value is null or invalid
 * @throws ArithmeticException if the new date-time exceeds the capacity
 */
public YearMonth withFieldAdded(DurationFieldType fieldType,int amount){
  int index=indexOfSupported(fieldType);
  if (amount == 0) {
    return this;
  }
  int[] newValues=getValues();
  newValues=getField(index).add(this,index,newValues,amount);
  return new YearMonth(this,newValues);
}
