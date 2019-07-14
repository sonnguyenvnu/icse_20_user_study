/** 
 * Returns a copy of this date with the month of year field updated. <p> YearMonthDay is immutable, so there are no set methods. Instead, this method returns a new instance with the value of month of year changed.
 * @param monthOfYear  the month of year to set
 * @return a copy of this object with the field set
 * @throws IllegalArgumentException if the value is invalid
 * @since 1.3
 */
public YearMonthDay withMonthOfYear(int monthOfYear){
  int[] newValues=getValues();
  newValues=getChronology().monthOfYear().set(this,MONTH_OF_YEAR,newValues,monthOfYear);
  return new YearMonthDay(this,newValues);
}
