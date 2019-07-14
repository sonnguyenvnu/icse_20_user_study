/** 
 * Unsafe version of:  {@link #object_getInstanceVariable} 
 */
public static long nobject_getInstanceVariable(long obj,long name,long outValue){
  long __functionAddress=Functions.object_getInstanceVariable;
  if (CHECKS) {
    check(obj);
  }
  return invokePPPP(obj,name,outValue,__functionAddress);
}
