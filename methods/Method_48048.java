@Override public boolean exists() throws BackendException {
  try {
    return clusterContext.getClient().describeKeyspace(keySpaceName) != null;
  }
 catch (  ConnectionException e) {
    throw new PermanentBackendException(e);
  }
}
