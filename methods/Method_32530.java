/** 
 * Returns a copy of this month-day with the specified field set to a new value. <p> For example, if the field type is <code>dayOfMonth</code> then the day would be changed in the returned instance. <p> These three lines are equivalent: <pre> MonthDay updated = md.withField(DateTimeFieldType.dayOfMonth(), 6); MonthDay updated = md.dayOfMonth().setCopy(6); MonthDay updated = md.property(DateTimeFieldType.dayOfMonth()).setCopy(6); </pre>
 * @param fieldType  the field type to set, not null
 * @param value  the value to set
 * @return a copy of this instance with the field set, never null
 * @throws IllegalArgumentException if the value is null or invalid
 */
public MonthDay withField(DateTimeFieldType fieldType,int value){
  int index=indexOfSupported(fieldType);
  if (value == getValue(index)) {
    return this;
  }
  int[] newValues=getValues();
  newValues=getField(index).set(this,index,newValues,value);
  return new MonthDay(this,newValues);
}
