public DbSqlBuilder columnValue(final Object value){
  return addChunk(new ColumnValueChunk(entityManager,null,value));
}
