/** 
 * Increment the count of performed synchronous state updates by  {@param num}.
 * @return The new total number of synchronous state updates recorded.
 */
public static long incStateUpdateSync(final long num){
  return sStateUpdatesSync.addAndGet(num);
}
