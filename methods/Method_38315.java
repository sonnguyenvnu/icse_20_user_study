/** 
 * Simply adds text without parsing to the query.
 */
public DbSqlBuilder appendRaw(final String text){
  addChunk(new RawSqlChunk(entityManager,text));
  return this;
}
