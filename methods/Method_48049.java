private void ensureColumnFamilyExists(String name) throws BackendException {
  Cluster cl=clusterContext.getClient();
  try {
    KeyspaceDefinition ksDef=cl.describeKeyspace(keySpaceName);
    boolean found=false;
    if (null != ksDef) {
      for (      ColumnFamilyDefinition cfDef : ksDef.getColumnFamilyList()) {
        found|=cfDef.getName().equals(name);
      }
    }
    if (!found) {
      ColumnFamilyDefinition cfDef=cl.makeColumnFamilyDefinition().setName(name).setKeyspace(keySpaceName).setComparatorType("org.apache.cassandra.db.marshal.BytesType");
      final ImmutableMap.Builder<String,String> compressionOptions=new ImmutableMap.Builder<>();
      if (storageConfig.has(COMPACTION_STRATEGY)) {
        cfDef.setCompactionStrategy(storageConfig.get(COMPACTION_STRATEGY));
      }
      if (!compactionOptions.isEmpty()) {
        cfDef.setCompactionStrategyOptions(compactionOptions);
      }
      if (compressionEnabled) {
        compressionOptions.put("sstable_compression",compressionClass).put("chunk_length_kb",Integer.toString(compressionChunkSizeKB));
      }
      cl.addColumnFamily(cfDef.setCompressionOptions(compressionOptions.build()));
    }
  }
 catch (  ConnectionException e) {
    throw new TemporaryBackendException(e);
  }
}
