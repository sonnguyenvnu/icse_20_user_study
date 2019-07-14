/** 
 * Gets the value of the field at the specified index. <p> This method is required to support the <code>ReadablePartial</code> interface. The supported fields are Year, MonthOfYear and DayOfMonth. Note that all fields from day and above may in fact be queried via other methods.
 * @param index  the index, zero to two
 * @return the value
 * @throws IndexOutOfBoundsException if the index is invalid
 */
public int getValue(int index){
switch (index) {
case YEAR:
    return getChronology().year().get(getLocalMillis());
case MONTH_OF_YEAR:
  return getChronology().monthOfYear().get(getLocalMillis());
case DAY_OF_MONTH:
return getChronology().dayOfMonth().get(getLocalMillis());
default :
throw new IndexOutOfBoundsException("Invalid index: " + index);
}
}
