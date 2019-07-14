@Override public void persistEphemeralSequential(final String key){
  try {
    client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(key);
  }
 catch (  final Exception ex) {
    RegExceptionHandler.handleException(ex);
  }
}
