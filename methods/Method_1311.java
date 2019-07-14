/** 
 * {@link Class#getSimpleName()} is not GWT compatible yet, so weprovide our own implementation.
 */
private static String simpleName(Class<?> clazz){
  String name=clazz.getName();
  name=name.replaceAll("\\$[0-9]+","\\$");
  int start=name.lastIndexOf('$');
  if (start == -1) {
    start=name.lastIndexOf('.');
  }
  return name.substring(start + 1);
}
