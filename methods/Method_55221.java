/** 
 * Returns an array of property attributes for a given property.
 * @param property the property whose attributes you want to copy
 * @return an array of property attributes. You must free the array with free().
 */
@Nullable @NativeType("objc_property_attribute_t *") public static ObjCPropertyAttribute.Buffer property_copyAttributeList(@NativeType("objc_property_t") long property){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  IntBuffer outCount=stack.callocInt(1);
  try {
    long __result=nproperty_copyAttributeList(property,memAddress(outCount));
    return ObjCPropertyAttribute.createSafe(__result,outCount.get(0));
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
