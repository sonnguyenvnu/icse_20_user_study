/** 
 * Gets the field for a specific index in the chronology specified. <p> This method must not use any instance variables.
 * @param index  the index to retrieve
 * @param chrono  the chronology to use
 * @return the field
 */
protected DateTimeField getField(int index,Chronology chrono){
switch (index) {
case HOUR_OF_DAY:
    return chrono.hourOfDay();
case MINUTE_OF_HOUR:
  return chrono.minuteOfHour();
case SECOND_OF_MINUTE:
return chrono.secondOfMinute();
case MILLIS_OF_SECOND:
return chrono.millisOfSecond();
default :
throw new IndexOutOfBoundsException("Invalid index: " + index);
}
}
