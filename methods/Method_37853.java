/** 
 * Prepares classname for loading, respecting the arrays. Returns <code>null</code> if class name is not an array.
 */
public static String prepareArrayClassnameForLoading(String className){
  int bracketCount=StringUtil.count(className,'[');
  if (bracketCount == 0) {
    return null;
  }
  String brackets=StringUtil.repeat('[',bracketCount);
  int bracketIndex=className.indexOf('[');
  className=className.substring(0,bracketIndex);
  int primitiveNdx=getPrimitiveClassNameIndex(className);
  if (primitiveNdx >= 0) {
    className=String.valueOf(PRIMITIVE_BYTECODE_NAME[primitiveNdx]);
    return brackets + className;
  }
 else {
    return brackets + 'L' + className + ';';
  }
}
