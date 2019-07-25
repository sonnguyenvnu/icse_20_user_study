@Override public AsyncFuture<Void> configure(final Session s){
  final Map<String,String> values=ImmutableMap.of("keyspace",keyspace);
  final AsyncFuture<PreparedStatement> createKeyspace;
  try {
    createKeyspace=prepareTemplate(values,s,CREATE_KEYSPACE_CQL);
  }
 catch (  IOException e) {
    return async.failed(e);
  }
  return createKeyspace.lazyTransform(createKeyspaceStmt -> {
    log.info("Creating keyspace {}",keyspace);
    return Async.bind(async,s.executeAsync(createKeyspaceStmt.bind())).lazyTransform(ign -> {
      final AsyncFuture<PreparedStatement> createTables=prepareTemplate(values,s,CREATE_TABLES_CQL);
      return createTables.lazyTransform(createTablesStmt -> {
        log.info("Creating tables for keyspace {}",keyspace);
        return Async.bind(async,s.executeAsync(createTablesStmt.bind())).directTransform(ign2 -> null);
      }
);
    }
);
  }
);
}
