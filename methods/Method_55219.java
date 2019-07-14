/** 
 * Unsafe version of:  {@link #property_getName} 
 */
public static long nproperty_getName(long property){
  long __functionAddress=Functions.property_getName;
  if (CHECKS) {
    check(property);
  }
  return invokePP(property,__functionAddress);
}
