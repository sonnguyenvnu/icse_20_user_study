/** 
 * Converts this duration to a period in hours assuming that there are the standard number of milliseconds in an hour. <p> This method assumes that there are 60 minutes in an hour, 60 seconds in a minute and 1000 milliseconds in a second. All currently supplied chronologies use this definition.
 * @return a period representing the number of standard hours in this period, never null
 * @throws ArithmeticException if the number of hours is too large to be represented
 * @since 2.0
 */
public Hours toStandardHours(){
  long hours=getStandardHours();
  return Hours.hours(FieldUtils.safeToInt(hours));
}
