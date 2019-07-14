/** 
 * Creates condition part of the query for id columns
 */
public DbSqlBuilder matchIds(final String tableRef,final Object value){
  return addChunk(new MatchChunk(entityManager,tableRef,value,SqlChunk.COLS_ONLY_IDS));
}
