/** 
 * Locates the target file from action path and the result value.
 */
protected String resolveTarget(final ActionRequest actionRequest,final String resultValue){
  String resultBasePath=actionRequest.getActionRuntime().getResultBasePath();
  ResultPath resultPath=resultMapper.resolveResultPath(resultBasePath,resultValue);
  String actionPath=resultPath.path();
  String path=actionPath;
  String value=resultPath.value();
  if (StringUtil.isEmpty(value)) {
    value=null;
  }
  String target;
  while (true) {
    if (value != null) {
      if (path == null) {
        int lastSlashNdx=actionPath.lastIndexOf('/');
        if (lastSlashNdx != -1) {
          target=actionPath.substring(0,lastSlashNdx + 1) + value;
        }
 else {
          target='/' + value;
        }
      }
 else {
        target=path + '.' + value;
      }
      target=locateTarget(actionRequest,target);
      if (target != null) {
        break;
      }
    }
    if (path != null) {
      target=locateTarget(actionRequest,path);
      if (target != null) {
        break;
      }
    }
    if (path == null) {
      return null;
    }
    int dotNdx=MadvocUtil.lastIndexOfDotAfterSlash(path);
    if (dotNdx == -1) {
      path=null;
    }
 else {
      path=path.substring(0,dotNdx);
    }
  }
  return target;
}
