@Override public int getNumChildren(final String key){
  try {
    Stat stat=client.checkExists().forPath(key);
    if (null != stat) {
      return stat.getNumChildren();
    }
  }
 catch (  final Exception ex) {
    RegExceptionHandler.handleException(ex);
  }
  return 0;
}
