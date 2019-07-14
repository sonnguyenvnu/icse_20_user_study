/** 
 * Unsafe version of:  {@link #property_copyAttributeList}
 * @param outCount the number of attributes returned in the array
 */
public static long nproperty_copyAttributeList(long property,long outCount){
  long __functionAddress=Functions.property_copyAttributeList;
  if (CHECKS) {
    check(property);
  }
  return invokePPP(property,outCount,__functionAddress);
}
