public DbSqlBuilder value(final String name,final Object value){
  return addChunk(new ValueChunk(entityManager,name,value));
}
