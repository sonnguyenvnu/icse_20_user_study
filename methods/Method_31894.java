/** 
 * Get the millis for the first week of a year.
 * @param year  the year to use
 * @return millis
 */
long getFirstWeekOfYearMillis(int year){
  long jan1millis=getYearMillis(year);
  int jan1dayOfWeek=getDayOfWeek(jan1millis);
  if (jan1dayOfWeek > (8 - iMinDaysInFirstWeek)) {
    return jan1millis + (8 - jan1dayOfWeek) * (long)DateTimeConstants.MILLIS_PER_DAY;
  }
 else {
    return jan1millis - (jan1dayOfWeek - 1) * (long)DateTimeConstants.MILLIS_PER_DAY;
  }
}
