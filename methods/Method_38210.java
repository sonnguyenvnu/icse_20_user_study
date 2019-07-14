/** 
 * Lookups  {@link DbEntityDescriptor} for some type and registers the type if is new.<p> Returns <code>null</code> for core classes from <code>java</code> run-time packages! Some types are <b>not</b> entities, i.e. domain objects. Instead, primitive entities are simply mapped to one column. 
 */
public <E>DbEntityDescriptor<E> lookupType(final Class<E> type){
  String typeName=type.getName();
  if (StringUtil.startsWithOne(typeName,primitiveEntitiesPrefixes) != -1) {
    return null;
  }
  DbEntityDescriptor<E> ded=descriptorsMap.get(type);
  if (ded == null) {
    ded=registerType(type);
  }
  return ded;
}
