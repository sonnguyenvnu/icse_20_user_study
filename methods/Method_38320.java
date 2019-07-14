public DbSqlBuilder table(final Object entity,final String alias,final String tableReference){
  return addChunk(new TableChunk(entityManager,entity,alias,tableReference));
}
