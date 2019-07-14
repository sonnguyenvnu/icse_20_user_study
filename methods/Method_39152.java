@Override public ActionDefinition buildActionDef(final Class actionClass,final Method actionMethod,final ActionNames actionNames){
  final String packageActionPath=actionNames.packageActionPath();
  final String classActionPath=actionNames.classActionPath();
  final String methodActionPath=actionNames.methodActionPath();
  final String httpMethod=actionNames.httpMethod();
  if (isAbsolutePath(methodActionPath)) {
    return createActionDef(methodActionPath,httpMethod,methodActionPath,actionNames);
  }
  String actionPath=classActionPath;
  if (methodActionPath != null) {
    if (!classActionPath.endsWith(StringPool.SLASH)) {
      actionPath+=StringPool.DOT;
    }
    actionPath+=methodActionPath;
  }
  if (isAbsolutePath(actionPath)) {
    return createActionDef(actionPath,httpMethod,actionPath,actionNames);
  }
  if (packageActionPath != null) {
    actionPath=packageActionPath + actionPath;
  }
 else {
    actionPath=StringPool.SLASH + actionPath;
  }
  return createActionDef(actionPath,httpMethod,actionPath,actionNames);
}
