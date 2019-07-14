/** 
 * Finds related entity.
 */
public <E>List<E> findRelated(final Class<E> target,final Object source){
  return query(dbOom.entities().findForeign(target,source)).autoClose().list(target);
}
