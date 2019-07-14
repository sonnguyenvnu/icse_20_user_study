/** 
 * Finds single entity by matching property.
 */
public <E>E findOneByProperty(final Class<E> entityType,final String name,final Object value){
  return query(dbOom.entities().findByColumn(entityType,name,value)).autoClose().find(entityType);
}
