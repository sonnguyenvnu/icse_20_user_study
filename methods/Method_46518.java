/** 
 * ????????????
 * @param fieldFullyQualifiedName fieldFullyQualifiedName
 * @return tableName
 */
public static String tableName(String fieldFullyQualifiedName){
  if (fieldFullyQualifiedName.contains(".")) {
    return fieldFullyQualifiedName.substring(0,fieldFullyQualifiedName.indexOf("."));
  }
  return null;
}
