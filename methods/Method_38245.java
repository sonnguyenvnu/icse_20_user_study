/** 
 * Finds generated key column of given type.
 */
public <T>T findGeneratedKey(final Class<T> type){
  return find(new Class[]{type},false,getGeneratedColumns());
}
