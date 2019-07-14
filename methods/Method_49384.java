private static void initializeTable(final Session session,final String keyspaceName,final String tableName,final Configuration configuration,final boolean allowCompactStorage){
  final Options createTable=createTable(keyspaceName,tableName).ifNotExists().addPartitionKey(KEY_COLUMN_NAME,DataType.blob()).addClusteringColumn(COLUMN_COLUMN_NAME,DataType.blob()).addColumn(VALUE_COLUMN_NAME,DataType.blob()).withOptions().compressionOptions(compressionOptions(configuration)).compactionOptions(compactionOptions(configuration));
  final boolean useCompactStorage=(allowCompactStorage && configuration.has(CF_COMPACT_STORAGE)) ? configuration.get(CF_COMPACT_STORAGE) : allowCompactStorage;
  session.execute(useCompactStorage ? createTable.compactStorage() : createTable);
}
