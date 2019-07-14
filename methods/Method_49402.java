@Override public boolean exists() throws BackendException {
  return cluster.getMetadata().getKeyspace(this.keyspace) != null;
}
