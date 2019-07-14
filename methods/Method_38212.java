/** 
 * Lookups for  {@link jodd.db.oom.DbEntityDescriptor} that was registered with this manager.Returns <code>null</code> if table name not found. Lookup is case-insensitive.
 */
public DbEntityDescriptor lookupTableName(final String tableName){
  return tableNamesMap.get(tableName);
}
