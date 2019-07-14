public DbSqlBuilder table(final Object entity,final String alias){
  return addChunk(new TableChunk(entityManager,entity,alias));
}
