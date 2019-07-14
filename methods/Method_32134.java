/** 
 * Calculates the astronomical Julian Day Number for an instant. <p> The  {@link #toJulianDay(long)} method calculates the astronomical Julian Daywith a fraction based on days starting at midday. This method calculates the variant where days start at midnight. JDN 0 is used for the date equivalent to Monday January 1, 4713 BC (Julian). Thus these days start 12 hours before those of the fractional Julian Day. <p> Note that this method has nothing to do with the day-of-year.
 * @param epochMillis  the epoch millis from 1970-01-01Z
 * @return the astronomical Julian Day represented by the specified instant
 * @since 2.2
 */
public static final long toJulianDayNumber(long epochMillis){
  return (long)Math.floor(toJulianDay(epochMillis) + 0.5d);
}
