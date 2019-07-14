/** 
 * Gets the field for a specific index in the chronology specified. <p> This method must not use any instance variables.
 * @param index  the index to retrieve
 * @param chrono  the chronology to use
 * @return the field
 */
protected DateTimeField getField(int index,Chronology chrono){
switch (index) {
case YEAR:
    return chrono.year();
case MONTH_OF_YEAR:
  return chrono.monthOfYear();
case DAY_OF_MONTH:
return chrono.dayOfMonth();
default :
throw new IndexOutOfBoundsException("Invalid index: " + index);
}
}
