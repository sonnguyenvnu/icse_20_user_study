/** 
 * Lookups for  {@link jodd.db.oom.DbEntityDescriptor} that was registered with this manager.Returns <code>null</code> if name not found.
 */
public DbEntityDescriptor lookupName(final String typeName){
  return entityNamesMap.get(typeName);
}
