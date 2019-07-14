/** 
 * Unsafe version of:  {@link #property_copyAttributeValue} 
 */
public static long nproperty_copyAttributeValue(long property,long attributeName){
  long __functionAddress=Functions.property_copyAttributeValue;
  if (CHECKS) {
    check(property);
  }
  return invokePPP(property,attributeName,__functionAddress);
}
