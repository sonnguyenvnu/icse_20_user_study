public DbSqlBuilder refId(final String tableRef){
  return addChunk(new ReferenceChunk(entityManager,tableRef,null,true));
}
