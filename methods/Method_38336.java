public DbSqlBuilder insert(final String entityName,final Object values){
  return addChunk(new InsertChunk(entityManager,dbOom.config().isUpdateablePrimaryKey(),entityName,values));
}
