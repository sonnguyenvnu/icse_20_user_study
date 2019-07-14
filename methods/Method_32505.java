/** 
 * Gets the value of the field at the specified index. <p> This method is required to support the <code>ReadablePartial</code> interface. The supported fields are HourOfDay, MinuteOfHour, SecondOfMinute and MillisOfSecond.
 * @param index  the index, zero to three
 * @return the value
 * @throws IndexOutOfBoundsException if the index is invalid
 */
public int getValue(int index){
switch (index) {
case HOUR_OF_DAY:
    return getChronology().hourOfDay().get(getLocalMillis());
case MINUTE_OF_HOUR:
  return getChronology().minuteOfHour().get(getLocalMillis());
case SECOND_OF_MINUTE:
return getChronology().secondOfMinute().get(getLocalMillis());
case MILLIS_OF_SECOND:
return getChronology().millisOfSecond().get(getLocalMillis());
default :
throw new IndexOutOfBoundsException("Invalid index: " + index);
}
}
