public DbSqlBuilder columnValue(final String name,final Object value){
  return addChunk(new ColumnValueChunk(entityManager,name,value));
}
