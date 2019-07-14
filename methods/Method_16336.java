private RDBDatabase initDatabase(DatabaseType databaseType,String databaseName){
  Supplier<AbstractRDBDatabaseMetaData> supplier=databaseMetaSuppliers.get(databaseType);
  Objects.requireNonNull(supplier,"database type" + databaseType + " is not support");
  AbstractRDBDatabaseMetaData metaData=supplier.get();
  metaData.setDatabaseName(databaseName);
  SimpleDatabase database=cluster ? new ClusterDatabase(metaData,sqlExecutor) : new SimpleDatabase(metaData,sqlExecutor);
  database.setAutoParse(true);
  eventPublisher.publishEvent(new DatabaseInitEvent(database));
  return database;
}
