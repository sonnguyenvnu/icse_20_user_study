/** 
 * Converts a 90 kHz clock timestamp to a timestamp in microseconds.
 * @param pts A 90 kHz clock timestamp.
 * @return The corresponding value in microseconds.
 */
public static long ptsToUs(long pts){
  return (pts * C.MICROS_PER_SECOND) / 90000;
}
