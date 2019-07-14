/** 
 * Converts local date time to epoh milliseconds.
 */
public static long toMilliseconds(final LocalDateTime localDateTime){
  return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
}
