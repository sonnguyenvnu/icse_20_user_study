/** 
 * Returns the time in microseconds for the given position in bytes.
 * @param position The position in bytes.
 */
public long getTimeUs(long position){
  long positionOffset=Math.max(0,position - dataStartPosition);
  return (positionOffset * C.MICROS_PER_SECOND) / averageBytesPerSecond;
}
