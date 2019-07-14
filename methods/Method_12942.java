/** 
 * Parse the given  {@code timeZoneString} value into a {@link TimeZone}.
 * @param timeZoneString the time zone {@code String}, following  {@link TimeZone#getTimeZone(String)}but throwing  {@link IllegalArgumentException} in case of an invalid time zone specification
 * @return a corresponding {@link TimeZone} instance
 * @throws IllegalArgumentException in case of an invalid time zone specification
 */
public static TimeZone parseTimeZoneString(String timeZoneString){
  TimeZone timeZone=TimeZone.getTimeZone(timeZoneString);
  if ("GMT".equals(timeZone.getID()) && !timeZoneString.startsWith("GMT")) {
    throw new IllegalArgumentException("Invalid time zone specification '" + timeZoneString + "'");
  }
  return timeZone;
}
