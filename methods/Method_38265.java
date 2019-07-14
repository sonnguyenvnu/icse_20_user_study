/** 
 * Applies column naming strategy to given column name hint. Returns full column name.
 */
public String applyToColumnName(final String columnName){
  String propertyName=convertColumnNameToPropertyName(columnName);
  return convertPropertyNameToColumnName(propertyName);
}
