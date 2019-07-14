public DbSqlBuilder table(final String entityName,final String alias){
  return addChunk(new TableChunk(entityManager,entityName,alias));
}
