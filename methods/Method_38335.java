public DbSqlBuilder columnValueRef(final String objectReference){
  return addChunk(new ColumnValueChunk(entityManager,objectReference));
}
