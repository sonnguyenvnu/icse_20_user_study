/** 
 * Unsafe version of:  {@link #class_replaceProperty}
 * @param attributeCount the number of attributes in {@code attributes}
 */
public static void nclass_replaceProperty(long cls,long name,long attributes,int attributeCount){
  long __functionAddress=Functions.class_replaceProperty;
  if (CHECKS) {
    check(cls);
    ObjCPropertyAttribute.validate(attributes,attributeCount);
  }
  invokePPPV(cls,name,attributes,attributeCount,__functionAddress);
}
