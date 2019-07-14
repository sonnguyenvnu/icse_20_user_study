/** 
 * Finds column descriptor by property name.
 */
public DbEntityColumnDescriptor findByPropertyName(final String propertyName){
  if (propertyName == null) {
    return null;
  }
  init();
  for (  DbEntityColumnDescriptor columnDescriptor : columnDescriptors) {
    if (columnDescriptor.propertyName.equals(propertyName)) {
      return columnDescriptor;
    }
  }
  return null;
}
