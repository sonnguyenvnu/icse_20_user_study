/** 
 * ??cglib??????
 * @param className ????
 * @return ??????
 */
public static boolean isCglibProxyClassName(String className){
  return (className != null && className.contains(CGLIB_CLASS_SEPARATOR));
}
