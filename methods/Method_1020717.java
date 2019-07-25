/** 
 * Filters out irrelevant stacktrace entries.
 * @param e the stacktrace element
 * @return true if the element may pass
 */
private static boolean filter(StackTraceElement e){
  if (e.getLineNumber() == 1) {
    return false;
  }
  String cn=e.getClassName();
  if (cn.contains("java.lang.Thread")) {
    return false;
  }
  if (cn.contains("junit.runner") || cn.contains("org.junit.internal") || cn.contains("junit4.runner")) {
    return false;
  }
  if (cn.contains("java.lang.reflect") || cn.contains("sun.reflect")) {
    return false;
  }
  if (cn.contains(".RxJavaAssemblyException")) {
    return false;
  }
  if (cn.contains("OnAssembly") || cn.contains("RxJavaAssemblyTracking") || cn.contains("RxJavaPlugins")) {
    return false;
  }
  return true;
}
