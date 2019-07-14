public DbSqlBuilder table(final Object entity){
  return addChunk(new TableChunk(entityManager,entity));
}
