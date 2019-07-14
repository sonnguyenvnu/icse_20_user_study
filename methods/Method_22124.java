@Override public long getRegistryCenterTime(final String key){
  long result=0L;
  try {
    persist(key,"");
    result=client.checkExists().forPath(key).getMtime();
  }
 catch (  final Exception ex) {
    RegExceptionHandler.handleException(ex);
  }
  Preconditions.checkState(0L != result,"Cannot get registry center time.");
  return result;
}
