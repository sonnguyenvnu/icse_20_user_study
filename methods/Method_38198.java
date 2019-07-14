/** 
 * Finds column descriptor by column name. Case is ignored.
 */
public DbEntityColumnDescriptor findByColumnName(final String columnName){
  if (columnName == null) {
    return null;
  }
  init();
  for (  DbEntityColumnDescriptor columnDescriptor : columnDescriptors) {
    if (columnDescriptor.columnName.equalsIgnoreCase(columnName)) {
      return columnDescriptor;
    }
  }
  return null;
}
