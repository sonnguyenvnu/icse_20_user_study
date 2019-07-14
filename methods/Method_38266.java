/** 
 * Converts entity name to table name.
 * @see #convertEntityNameToTableName(String)
 */
public String convertEntityNameToTableName(final Class type){
  return convertEntityNameToTableName(type.getSimpleName());
}
