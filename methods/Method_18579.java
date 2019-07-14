/** 
 * Increment the count of performed lazy state updates by  {@param num}.
 * @return The new total number of lazy state updates recorded.
 */
public static long incStateUpdateLazy(final long num){
  return sStateUpdatesLazy.addAndGet(num);
}
