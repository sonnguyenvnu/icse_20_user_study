public DbSqlBuilder from(final Class entityType){
  return from(entityType,createTableRefName(entityType));
}
