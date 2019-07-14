/** 
 * Gets the value of the field at the specified index. <p> This method is required to support the <code>ReadablePartial</code> interface. The supported fields are Year, MonthOfDay, DayOfMonth and MillisOfDay.
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
case MILLIS_OF_DAY:
return getChronology().millisOfDay().get(getLocalMillis());
default :
throw new IndexOutOfBoundsException("Invalid index: " + index);
}
}
