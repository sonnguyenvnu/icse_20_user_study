/** 
 * @param columnName title of the column to search
 */
public TableRow matchRow(String regexp,String columnName){
  return matchRow(regexp,getColumnIndex(columnName));
}
