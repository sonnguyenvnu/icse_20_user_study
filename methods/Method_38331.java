public DbSqlBuilder value(final Object value){
  return addChunk(new ValueChunk(entityManager,null,value));
}
