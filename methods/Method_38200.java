/** 
 * Returns property name for specified column name.
 */
public String getPropertyName(final String columnName){
  DbEntityColumnDescriptor dec=findByColumnName(columnName);
  return dec == null ? null : dec.propertyName;
}
