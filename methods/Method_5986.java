/** 
 * Converts a timestamp in microseconds to a 90 kHz clock timestamp.
 * @param us A value in microseconds.
 * @return The corresponding value as a 90 kHz clock timestamp.
 */
public static long usToPts(long us){
  return (us * 90000) / C.MICROS_PER_SECOND;
}
