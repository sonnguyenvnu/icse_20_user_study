@Override public String getDirectly(final String key){
  try {
    return new String(client.getData().forPath(key),Charsets.UTF_8);
  }
 catch (  final Exception ex) {
    RegExceptionHandler.handleException(ex);
    return null;
  }
}
