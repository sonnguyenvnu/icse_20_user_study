Map<String,String> getCompressionOptions(final String name) throws BackendException {
  final KeyspaceMetadata keyspaceMetadata=Option.of(this.cluster.getMetadata().getKeyspace(this.keyspace)).getOrElseThrow(() -> new PermanentBackendException(String.format("Unknown keyspace '%s'",this.keyspace)));
  return Option.of(keyspaceMetadata.getTable(name)).map(tableMetadata -> tableMetadata.getOptions().getCompression()).getOrElseThrow(() -> new PermanentBackendException(String.format("Unknown table '%s'",name)));
}
