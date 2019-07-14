/** 
 * Gets the millisecond length of the interval.
 * @param object  the interval
 */
public long getDurationMillis(Object object){
  return (((ReadableInterval)object)).toDurationMillis();
}
