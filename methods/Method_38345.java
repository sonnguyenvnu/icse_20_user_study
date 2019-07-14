/** 
 * Returns entity descriptor for provided table reference.
 */
public DbEntityDescriptor getTableDescriptor(final String tableRef){
  if (tableRefs == null) {
    return null;
  }
  TableRefData t=tableRefs.get(tableRef);
  return t == null ? null : t.desc;
}
