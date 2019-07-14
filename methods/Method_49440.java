@Override public boolean exists() throws BackendException {
  try {
    return client.indexExists(indexName);
  }
 catch (  final IOException e) {
    throw new PermanentBackendException("Could not check if index " + indexName + " exists",e);
  }
}
