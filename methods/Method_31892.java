/** 
 * Get the number of days in the year.
 * @param year  the year to use
 * @return 366 if a leap year, otherwise 365
 */
int getDaysInYear(int year){
  return isLeapYear(year) ? 366 : 365;
}
