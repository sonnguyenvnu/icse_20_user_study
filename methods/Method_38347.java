/** 
 * Returns table alias for provided table reference.
 */
public String getTableAlias(final String tableRef){
  if (tableRefs == null) {
    return null;
  }
  TableRefData t=tableRefs.get(tableRef);
  return t == null ? null : t.alias;
}
