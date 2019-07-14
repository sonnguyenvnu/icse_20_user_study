/** 
 * Converts type to byteccode type ref.
 */
public static String typeToTyperef(final Class type){
  if (!type.isArray()) {
    if (!type.isPrimitive()) {
      return 'L' + typeToSignature(type) + ';';
    }
    if (type == int.class) {
      return "I";
    }
    if (type == long.class) {
      return "J";
    }
    if (type == boolean.class) {
      return "Z";
    }
    if (type == double.class) {
      return "D";
    }
    if (type == float.class) {
      return "F";
    }
    if (type == short.class) {
      return "S";
    }
    if (type == void.class) {
      return "V";
    }
    if (type == byte.class) {
      return "B";
    }
    if (type == char.class) {
      return "C";
    }
  }
  return type.getName();
}
