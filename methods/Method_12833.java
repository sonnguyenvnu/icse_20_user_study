/** 
 * Get a wrapper type for a primitive type, or the argument type itself, if it is not a primitive type.
 */
public static Class<?> wrapper(Class<?> type){
  if (type == null) {
    return null;
  }
 else   if (type.isPrimitive()) {
    if (boolean.class == type) {
      return Boolean.class;
    }
 else     if (int.class == type) {
      return Integer.class;
    }
 else     if (long.class == type) {
      return Long.class;
    }
 else     if (short.class == type) {
      return Short.class;
    }
 else     if (byte.class == type) {
      return Byte.class;
    }
 else     if (double.class == type) {
      return Double.class;
    }
 else     if (float.class == type) {
      return Float.class;
    }
 else     if (char.class == type) {
      return Character.class;
    }
 else     if (void.class == type) {
      return Void.class;
    }
  }
  return type;
}
