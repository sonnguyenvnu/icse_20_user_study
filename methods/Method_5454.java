/** 
 * Returns the timestamp of the first sample, or  {@link Long#MIN_VALUE} if the queue is empty. 
 */
public synchronized long getFirstTimestampUs(){
  return length == 0 ? Long.MIN_VALUE : timesUs[relativeFirstIndex];
}
