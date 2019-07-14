/** 
 * Converts this duration to a period in minutes assuming that there are the standard number of milliseconds in a minute. <p> This method assumes that there are 60 seconds in a minute and 1000 milliseconds in a second. All currently supplied chronologies use this definition.
 * @return a period representing the number of standard minutes in this period, never null
 * @throws ArithmeticException if the number of minutes is too large to be represented
 * @since 2.0
 */
public Minutes toStandardMinutes(){
  long minutes=getStandardMinutes();
  return Minutes.minutes(FieldUtils.safeToInt(minutes));
}
