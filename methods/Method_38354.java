/** 
 * Lookups for table reference and throws an exception if table reference not found.
 */
protected DbEntityDescriptor lookupTableRef(final String tableRef){
  DbEntityDescriptor ded=getTableDescriptor(tableRef);
  if (ded == null) {
    throw new DbSqlBuilderException("Table reference not used in this query: " + tableRef);
  }
  return ded;
}
