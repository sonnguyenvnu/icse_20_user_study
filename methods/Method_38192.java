/** 
 * List all entities.
 */
public <E>List<E> listAll(final Class<E> target){
  return query(dbOom.entities().from(target)).autoClose().list(target);
}
