/** 
 * ?????
 * @return ?????cache boolean
 */
public boolean hasCache(){
  if (isCache()) {
    return true;
  }
  if (CommonUtils.isNotEmpty(methods)) {
    for (    MethodConfig methodConfig : methods.values()) {
      if (CommonUtils.isTrue(methodConfig.getCache())) {
        return true;
      }
    }
  }
  return false;
}
