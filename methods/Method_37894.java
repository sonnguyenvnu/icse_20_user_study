/** 
 * Returns <code>enum</code> class or <code>null</code> if class is not an enum.
 */
public static Class findEnum(Class target){
  if (target.isPrimitive()) {
    return null;
  }
  while (target != Object.class) {
    if (target.isEnum()) {
      return target;
    }
    target=target.getSuperclass();
  }
  return null;
}
