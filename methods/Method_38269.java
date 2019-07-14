/** 
 * Applies table naming strategy to given table name hint. Returns full table name.
 */
public String applyToTableName(final String tableName){
  String entityName=convertTableNameToEntityName(tableName);
  return convertEntityNameToTableName(entityName);
}
