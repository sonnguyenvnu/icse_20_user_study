/** 
 * @return the global count of lazy state updates that have happened in the process. 
 */
public static long getStateUpdatesLazy(){
  return sStateUpdatesLazy.get();
}
