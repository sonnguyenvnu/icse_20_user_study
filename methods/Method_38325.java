/** 
 * Creates condition part of the query only for existing columns.
 */
public DbSqlBuilder match(final String tableRef,final Object value){
  return addChunk(new MatchChunk(entityManager,tableRef,value,SqlChunk.COLS_ONLY_EXISTING));
}
