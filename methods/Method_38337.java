public DbSqlBuilder insert(final Class entity,final Object values){
  return addChunk(new InsertChunk(entityManager,dbOom.config().isUpdateablePrimaryKey(),entity,values));
}
