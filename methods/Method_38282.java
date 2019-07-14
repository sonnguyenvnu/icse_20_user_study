/** 
 * Lookups for table reference and optionally throws an exception if table reference not found.
 */
protected DbEntityDescriptor lookupTableRef(final String tableRef,final boolean throwExceptionIfNotFound){
  DbEntityDescriptor ded=templateData.getTableDescriptor(tableRef);
  if (ded == null) {
    if (throwExceptionIfNotFound) {
      throw new DbSqlBuilderException("Invalid table reference: " + tableRef);
    }
  }
  return ded;
}
