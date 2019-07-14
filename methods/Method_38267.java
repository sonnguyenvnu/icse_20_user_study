/** 
 * Converts entity (type) name to table name.
 */
public String convertEntityNameToTableName(String entityName){
  int ndx=entityName.indexOf(entityNameTerminator);
  if (ndx != -1) {
    entityName=entityName.substring(0,ndx);
  }
  StringBuilder tableName=new StringBuilder(entityName.length() * 2);
  if (prefix != null) {
    tableName.append(prefix);
  }
  if (splitCamelCase) {
    String convertedTableName=Format.fromCamelCase(entityName,separatorChar);
    tableName.append(convertedTableName);
  }
 else {
    tableName.append(entityName);
  }
  if (suffix != null) {
    tableName.append(suffix);
  }
  if (!changeCase) {
    return tableName.toString();
  }
  return uppercase ? toUppercase(tableName).toString() : toLowercase(tableName).toString();
}
