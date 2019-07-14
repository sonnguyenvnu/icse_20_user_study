private void ensureColumnFamilyExists(String keyspaceName,String columnFamilyName) throws BackendException {
  if (null != Schema.instance.getCFMetaData(keyspaceName,columnFamilyName))   return;
  final CFMetaData cfm=new CFMetaData(keyspaceName,columnFamilyName,ColumnFamilyType.Standard,CellNames.fromAbstractType(BytesType.instance,true));
  try {
    if (storageConfig.has(COMPACTION_STRATEGY)) {
      cfm.compactionStrategyClass(CFMetaData.createCompactionStrategy(storageConfig.get(COMPACTION_STRATEGY)));
    }
    if (!compactionOptions.isEmpty()) {
      cfm.compactionStrategyOptions(compactionOptions);
    }
  }
 catch (  ConfigurationException e) {
    throw new PermanentBackendException("Failed to create column family metadata for " + keyspaceName + ":" + columnFamilyName,e);
  }
  if (columnFamilyName.startsWith(Backend.EDGESTORE_NAME)) {
    cfm.caching(CachingOptions.KEYS_ONLY);
  }
 else   if (columnFamilyName.startsWith(Backend.INDEXSTORE_NAME)) {
    cfm.caching(CachingOptions.ROWS_ONLY);
  }
  final CompressionParameters cp;
  if (compressionEnabled) {
    try {
      cp=new CompressionParameters(compressionClass,compressionChunkSizeKB * 1024,Collections.emptyMap());
      log.debug("Creating CF {}: setting {}={} and {}={} on {}",columnFamilyName,CompressionParameters.SSTABLE_COMPRESSION,compressionClass,CompressionParameters.CHUNK_LENGTH_KB,compressionChunkSizeKB,cp);
    }
 catch (    ConfigurationException ce) {
      throw new PermanentBackendException(ce);
    }
  }
 else {
    cp=new CompressionParameters(null);
    log.debug("Creating CF {}: setting {} to null to disable compression",columnFamilyName,CompressionParameters.SSTABLE_COMPRESSION);
  }
  cfm.compressionParameters(cp);
  try {
    cfm.addDefaultIndexNames();
  }
 catch (  ConfigurationException e) {
    throw new PermanentBackendException("Failed to create column family metadata for " + keyspaceName + ":" + columnFamilyName,e);
  }
  try {
    MigrationManager.announceNewColumnFamily(cfm);
    log.info("Created CF {} in KS {}",columnFamilyName,keyspaceName);
  }
 catch (  ConfigurationException e) {
    throw new PermanentBackendException("Failed to create column family " + keyspaceName + ":" + columnFamilyName,e);
  }
  retryDummyRead(keyspaceName,columnFamilyName);
}
