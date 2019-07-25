/** 
 * Unary promotion. All Objects are promoted to Object. 
 */
private static Class<?> promote(Class<?> clazz){
  if (clazz.isPrimitive() == false) {
    return Object.class;
  }
  if (clazz == byte.class || clazz == short.class || clazz == char.class || clazz == int.class) {
    return int.class;
  }
 else {
    return clazz;
  }
}
