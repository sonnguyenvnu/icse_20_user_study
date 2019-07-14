@Override public boolean hasTimeout(){
  if (timeout > 0) {
    return true;
  }
  if (CommonUtils.isNotEmpty(methods)) {
    for (    MethodConfig methodConfig : methods.values()) {
      if (methodConfig.getTimeout() != null && methodConfig.getTimeout() > 0) {
        return true;
      }
    }
  }
  return false;
}
