public DbSqlBuilder valueRef(final String objectReference){
  return addChunk(new ValueChunk(entityManager,objectReference));
}
