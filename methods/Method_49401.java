@Override public void clearStorage() throws BackendException {
  if (this.storageConfig.get(DROP_ON_CLEAR)) {
    this.session.execute(dropKeyspace(this.keyspace));
  }
 else   if (this.exists()) {
    final Future<Seq<ResultSet>> result=Future.sequence(Iterator.ofAll(this.cluster.getMetadata().getKeyspace(this.keyspace).getTables()).map(table -> Future.fromJavaFuture(this.session.executeAsync(truncate(this.keyspace,table.getName())))));
    result.await();
  }
 else {
    LOGGER.info("Keyspace {} does not exist in the cluster",this.keyspace);
  }
}
