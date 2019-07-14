/** 
 * Unsafe version of:  {@link #object_setInstanceVariable} 
 */
public static long nobject_setInstanceVariable(long obj,long name,long value){
  long __functionAddress=Functions.object_setInstanceVariable;
  if (CHECKS) {
    check(obj);
  }
  return invokePPPP(obj,name,value,__functionAddress);
}
