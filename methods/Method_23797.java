/** 
 * @param columnName title of the column to search
 */
public Iterable<TableRow> matchRows(String regexp,String columnName){
  return matchRows(regexp,getColumnIndex(columnName));
}
