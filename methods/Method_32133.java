/** 
 * Calculates the astronomical Julian Day for an instant. <p> The <a href="https://en.wikipedia.org/wiki/Julian_day">Julian day</a> is a well-known system of time measurement for scientific use by the astronomy community. It expresses the interval of time in days and fractions of a day since January 1, 4713 BC (Julian) Greenwich noon. <p> Each day starts at midday (not midnight) and time is expressed as a fraction. Thus the fraction 0.25 is 18:00. equal to one quarter of the day from midday to midday. <p> Note that this method has nothing to do with the day-of-year.
 * @param epochMillis  the epoch millis from 1970-01-01Z
 * @return the astronomical Julian Day represented by the specified instant
 * @since 2.2
 */
public static final double toJulianDay(long epochMillis){
  double epochDay=epochMillis / 86400000d;
  return epochDay + 2440587.5d;
}
