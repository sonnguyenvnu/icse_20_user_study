/** 
 * Unsafe version of:  {@link #XCreateColormap} 
 */
public static long nXCreateColormap(long display,long w,long visual,int alloc){
  long __functionAddress=Functions.XCreateColormap;
  if (CHECKS) {
    check(display);
  }
  return invokePPPP(display,w,visual,alloc,__functionAddress);
}
