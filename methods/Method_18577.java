/** 
 * Increment the count of performed state updates by  {@param num}.
 * @return The new total number of all state updates recorded.
 */
public static long incStateUpdate(final long num){
  return sStateUpdates.addAndGet(num);
}
