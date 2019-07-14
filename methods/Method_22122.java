@Override public String persistSequential(final String key,final String value){
  try {
    return client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(key,value.getBytes(Charsets.UTF_8));
  }
 catch (  final Exception ex) {
    RegExceptionHandler.handleException(ex);
  }
  return null;
}
