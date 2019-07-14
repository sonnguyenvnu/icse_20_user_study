/** 
 * Creates a date-time from a Julian Day. <p> Returns the  {@code DateTime} object equal to the specified Julian Day.
 * @param julianDay  the Julian Day
 * @return the epoch millis from 1970-01-01Z
 * @since 2.2
 */
public static final long fromJulianDay(double julianDay){
  double epochDay=julianDay - 2440587.5d;
  return (long)(epochDay * 86400000d);
}
