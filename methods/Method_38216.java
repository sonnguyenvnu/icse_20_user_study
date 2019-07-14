/** 
 * Removes entity and returns removed descriptor.
 */
public <E>DbEntityDescriptor<E> removeEntity(final Class<E> type){
  DbEntityDescriptor<E> ded=descriptorsMap.remove(type);
  if (ded == null) {
    ded=createDbEntityDescriptor(type);
  }
  entityNamesMap.remove(ded.getEntityName());
  tableNamesMap.remove(ded.getTableName());
  return ded;
}
