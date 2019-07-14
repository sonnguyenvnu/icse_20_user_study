/** 
 * ????method configs ????
 * @param methodConfigs
 * @param methodName
 * @return
 */
private boolean inMethodConfigs(String methodConfigs,String methodName){
  String[] excludeMethodCollections=StringUtils.splitWithCommaOrSemicolon(methodConfigs);
  for (  String excludeMethodName : excludeMethodCollections) {
    boolean exist=StringUtils.equals(excludeMethodName,methodName);
    if (exist) {
      return true;
    }
  }
  return false;
}
