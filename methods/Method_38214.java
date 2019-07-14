/** 
 * Registers entity.  {@link #registerType(Class) Registers types} and table names.
 */
public <E>DbEntityDescriptor<E> registerEntity(final Class<E> type){
  DbEntityDescriptor<E> ded=registerType(type);
  DbEntityDescriptor existing=tableNamesMap.put(ded.getTableName(),ded);
  if (existing != null) {
    if (ded.getType() == type) {
      return ded;
    }
    throw new DbOomException("Entity registration failed! Table '" + ded.getTableName() + "' already mapped to an entity: " + existing.getType());
  }
  return ded;
}
