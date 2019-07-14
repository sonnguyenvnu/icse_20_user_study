/** 
 * Finds generated columns.
 */
public Object findGeneratedColumns(final Class... types){
  return find(types,false,getGeneratedColumns());
}
