/** 
 * ??Class??
 * @param typeStr ????
 * @return ?
 */
public static Class getClassCache(String typeStr){
  ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
  if (classLoader == null) {
    return null;
  }
 else {
    Map<ClassLoader,Class> temp=CLASS_CACHE.get(typeStr);
    return temp == null ? null : temp.get(classLoader);
  }
}
