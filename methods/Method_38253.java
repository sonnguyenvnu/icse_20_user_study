/** 
 * Creates table names for given types.
 */
protected String[] createTypesTableNames(final Class[] types){
  String[] names=new String[types.length];
  for (int i=0; i < types.length; i++) {
    if (types[i] == null) {
      names[i]=null;
      continue;
    }
    DbEntityDescriptor ded=dbEntityManager.lookupType(types[i]);
    if (ded != null) {
      String tableName=ded.getTableName();
      tableName=tableName.toUpperCase();
      names[i]=tableName;
    }
  }
  return names;
}
