/** 
 * Unsafe version of:  {@link #protocol_addProperty}
 * @param attributeCount the number of properties in {@code attributes}
 */
public static void nprotocol_addProperty(long proto,long name,long attributes,int attributeCount,boolean isRequiredProperty,boolean isInstanceProperty){
  long __functionAddress=Functions.protocol_addProperty;
  if (CHECKS) {
    check(proto);
    ObjCPropertyAttribute.validate(attributes,attributeCount);
  }
  invokePPPV(proto,name,attributes,attributeCount,isRequiredProperty,isInstanceProperty,__functionAddress);
}
