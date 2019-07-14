/** 
 * Creates  {@link DbEntityDescriptor}.
 */
protected <E>DbEntityDescriptor<E> createDbEntityDescriptor(final Class<E> type){
  final String schemaName=dbOomConfig.getSchemaName();
  final TableNamingStrategy tableNames=dbOomConfig.getTableNames();
  final ColumnNamingStrategy columnNames=dbOomConfig.getColumnNames();
  return new DbEntityDescriptor<>(type,schemaName,tableNames,columnNames);
}
