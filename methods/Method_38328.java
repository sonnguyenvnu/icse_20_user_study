public DbSqlBuilder matchIds(final String tableRef,final String objectRef){
  return addChunk(new MatchChunk(entityManager,tableRef,objectRef,SqlChunk.COLS_ONLY_IDS));
}
