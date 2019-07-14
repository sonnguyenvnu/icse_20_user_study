/** 
 * Finds a table that contains given column.
 */
protected DbEntityDescriptor findColumnRef(final String columnRef){
  DbEntityDescriptor ded=templateData.findTableDescriptorByColumnRef(columnRef);
  if (ded == null) {
    throw new DbSqlBuilderException("Invalid column reference: [" + columnRef + "]");
  }
  return ded;
}
