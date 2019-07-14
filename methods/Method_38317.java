public DbSqlBuilder table(final String entityName){
  return addChunk(new TableChunk(entityManager,entityName));
}
