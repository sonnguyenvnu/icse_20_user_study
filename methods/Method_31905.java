/** 
 * Gets the milliseconds for a date at midnight.
 * @param year  the year
 * @param monthOfYear  the month
 * @param dayOfMonth  the day
 * @return the milliseconds
 */
long getDateMidnightMillis(int year,int monthOfYear,int dayOfMonth){
  FieldUtils.verifyValueBounds(DateTimeFieldType.year(),year,getMinYear() - 1,getMaxYear() + 1);
  FieldUtils.verifyValueBounds(DateTimeFieldType.monthOfYear(),monthOfYear,1,getMaxMonth(year));
  FieldUtils.verifyValueBounds(DateTimeFieldType.dayOfMonth(),dayOfMonth,1,getDaysInYearMonth(year,monthOfYear));
  long instant=getYearMonthDayMillis(year,monthOfYear,dayOfMonth);
  if (instant < 0 && year == getMaxYear() + 1) {
    return Long.MAX_VALUE;
  }
 else   if (instant > 0 && year == getMinYear() - 1) {
    return Long.MIN_VALUE;
  }
  return instant;
}
