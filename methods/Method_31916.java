/** 
 * Gets the number of days in the specified month and year.
 * @param year  the year
 * @param month  the month
 * @return the number of days
 */
int getDaysInYearMonth(int year,int month){
  if (isLeapYear(year)) {
    return MAX_DAYS_PER_MONTH_ARRAY[month - 1];
  }
 else {
    return MIN_DAYS_PER_MONTH_ARRAY[month - 1];
  }
}
