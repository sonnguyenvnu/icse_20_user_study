/** 
 * Creates new entity instances.
 */
public <E>E createEntityInstance(final Class<E> type){
  try {
    return ClassUtil.newInstance(type);
  }
 catch (  Exception ex) {
    throw new DbOomException(ex);
  }
}
