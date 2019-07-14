/** 
 * Class?String<br> ??????String????????Class.forName????getClass(String)????
 * @param clazz Class
 * @return ??
 * @see #getClass(String)
 */
public static String getTypeStr(Class clazz){
  String typeStr=ReflectCache.getTypeStrCache(clazz);
  if (typeStr == null) {
    if (clazz.isArray()) {
      String name=clazz.getName();
      typeStr=jvmNameToCanonicalName(name);
    }
 else {
      typeStr=clazz.getName();
    }
    ReflectCache.putTypeStrCache(clazz,typeStr);
  }
  return typeStr;
}
