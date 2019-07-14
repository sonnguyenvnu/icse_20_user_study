/** 
 * Creates table names for all specified types. Since this is usually done once per result set, these names are cached. Type name will be <code>null</code> for simple names, i.e. for all those types that returns <code>null</code> when used by  {@link DbEntityManager#lookupType(Class)}.
 */
protected String[] resolveTypesTableNames(final Class[] types){
  if (types != cachedUsedTypes) {
    cachedTypesTableNames=createTypesTableNames(types);
    cachedUsedTypes=types;
  }
  return cachedTypesTableNames;
}
