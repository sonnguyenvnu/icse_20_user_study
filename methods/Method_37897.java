/** 
 * Resolves class file name from class name by replacing dot's with '/' separator and adding class extension at the end. If array, component type is returned.
 */
public static String convertClassNameToFileName(Class clazz){
  if (clazz.isArray()) {
    clazz=clazz.getComponentType();
  }
  return convertClassNameToFileName(clazz.getName());
}
