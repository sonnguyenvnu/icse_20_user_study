@Override public boolean isAlias(String aliasName){
  boolean exists=false;
  try {
    delegate.performRequest(REQUEST_TYPE_GET,REQUEST_SEPARATOR + "_alias" + REQUEST_SEPARATOR + aliasName);
    exists=true;
  }
 catch (  final IOException ignored) {
  }
  return exists;
}
