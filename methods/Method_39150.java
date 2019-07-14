/** 
 * Replaces action path macros in the path. If one of the provided paths is <code>null</code> it will not be replaced - so to emphasize the problem.
 */
protected String replaceActionNameMacros(String path,final ActionNames actionNames){
  final String packageName=actionNames.packageName();
  final String className=actionNames.className();
  final String methodName=actionNames.methodName();
  final String httpMethod=actionNames.httpMethod();
  if (packageName != null) {
    path=StringUtil.replace(path,PACKAGE_MACRO,packageName);
  }
  if (className != null) {
    path=StringUtil.replace(path,CLASS_MACRO,className);
  }
  if (methodName != null) {
    path=StringUtil.replace(path,METHOD_MACRO,methodName);
  }
  if (httpMethod != null) {
    path=StringUtil.replace(path,HTTPMETHOD_MACRO,httpMethod);
  }
  return path;
}
