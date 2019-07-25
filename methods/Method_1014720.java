/** 
 * Returns the number of thread local variables bound to the current thread.
 */
public static int size(){
  InternalThreadLocalMap threadLocalMap=InternalThreadLocalMap.getIfSet();
  if (threadLocalMap == null) {
    return 0;
  }
 else {
    return threadLocalMap.size();
  }
}
