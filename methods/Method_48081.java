private void createColumnFamily(Cassandra.Client client,String ksName,String cfName,String comparator) throws BackendException {
  CfDef createColumnFamily=new CfDef();
  createColumnFamily.setName(cfName);
  createColumnFamily.setKeyspace(ksName);
  createColumnFamily.setComparator_type(comparator);
  if (storageConfig.has(COMPACTION_STRATEGY)) {
    createColumnFamily.setCompaction_strategy(storageConfig.get(COMPACTION_STRATEGY));
  }
  if (!compactionOptions.isEmpty()) {
    createColumnFamily.setCompaction_strategy_options(compactionOptions);
  }
  final ImmutableMap.Builder<String,String> compressionOptions=new ImmutableMap.Builder<>();
  if (compressionEnabled) {
    compressionOptions.put("sstable_compression",compressionClass).put("chunk_length_kb",Integer.toString(compressionChunkSizeKB));
  }
  createColumnFamily.setCompression_options(compressionOptions.build());
  if (cfName.startsWith(Backend.EDGESTORE_NAME)) {
    createColumnFamily.setCaching("keys_only");
  }
 else   if (cfName.startsWith(Backend.INDEXSTORE_NAME)) {
    createColumnFamily.setCaching("rows_only");
  }
  log.debug("Adding column family {} to keyspace {}...",cfName,ksName);
  try {
    client.system_add_column_family(createColumnFamily);
  }
 catch (  SchemaDisagreementException e) {
    throw new TemporaryBackendException("Error in setting up column family",e);
  }
catch (  Exception e) {
    throw new PermanentBackendException(e);
  }
  log.debug("Added column family {} to keyspace {}.",cfName,ksName);
}
