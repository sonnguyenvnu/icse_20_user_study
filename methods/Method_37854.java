/** 
 * Detects if provided class name is a primitive type. Returns >= 0 number if so.
 */
private static int getPrimitiveClassNameIndex(final String className){
  int dotIndex=className.indexOf('.');
  if (dotIndex != -1) {
    return -1;
  }
  return Arrays.binarySearch(PRIMITIVE_TYPE_NAMES,className);
}
