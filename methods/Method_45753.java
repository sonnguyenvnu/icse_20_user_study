/** 
 * ???????????+string+date
 * @param clazz the cls
 * @return the boolean
 */
public static boolean isPrimitives(Class<?> clazz){
  if (clazz.isArray()) {
    return isPrimitiveType(clazz.getComponentType());
  }
  return isPrimitiveType(clazz);
}
