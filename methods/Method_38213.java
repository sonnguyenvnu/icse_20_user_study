/** 
 * Registers just type and entity names. Enough for most usages.
 */
public <E>DbEntityDescriptor<E> registerType(final Class<E> type){
  DbEntityDescriptor<E> ded=createDbEntityDescriptor(type);
  DbEntityDescriptor<E> existing=descriptorsMap.put(type,ded);
  if (log.isDebugEnabled()) {
    log.debug("Register " + type.getName() + " as " + ded.getTableName());
  }
  if (existing != null) {
    if (ded.getType() == type) {
      return ded;
    }
    throw new DbOomException("Type already registered: " + existing.getType());
  }
  existing=entityNamesMap.put(ded.getEntityName(),ded);
  if (existing != null) {
    throw new DbOomException("Name '" + ded.getEntityName() + "' already mapped to an entity: " + existing.getType());
  }
  return ded;
}
