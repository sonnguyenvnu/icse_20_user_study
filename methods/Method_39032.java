/** 
 * Reads action path from the action method.
 */
protected String[] readMethodActionPath(final String methodName,final ActionAnnotationValues annotationValues,final ActionConfig actionConfig){
  String methodActionPath=annotationValues != null ? annotationValues.value() : null;
  if (methodActionPath == null) {
    methodActionPath=methodName;
  }
 else {
    if (methodActionPath.equals(Action.NONE)) {
      return ArraysUtil.array(null,null);
    }
  }
  for (  String path : actionConfig.getActionMethodNames()) {
    if (methodActionPath.equals(path)) {
      methodActionPath=null;
      break;
    }
  }
  return ArraysUtil.array(methodName,methodActionPath);
}
