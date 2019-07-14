/** 
 * Returns a copy of this time with the specified field set to a new value. <p> For example, if the field type is <code>minuteOfHour</code> then the day would be changed in the returned instance. <p> These three lines are equivalent: <pre> TimeOfDay updated = tod.withField(DateTimeFieldType.minuteOfHour(), 6); TimeOfDay updated = tod.minuteOfHour().setCopy(6); TimeOfDay updated = tod.property(DateTimeFieldType.minuteOfHour()).setCopy(6); </pre>
 * @param fieldType  the field type to set, not null
 * @param value  the value to set
 * @return a copy of this instance with the field set
 * @throws IllegalArgumentException if the value is null or invalid
 */
public TimeOfDay withField(DateTimeFieldType fieldType,int value){
  int index=indexOfSupported(fieldType);
  if (value == getValue(index)) {
    return this;
  }
  int[] newValues=getValues();
  newValues=getField(index).set(this,index,newValues,value);
  return new TimeOfDay(this,newValues);
}
