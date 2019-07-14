/** 
 * @param columnName title of the column to search
 */
public Iterator<TableRow> matchRowIterator(String value,String columnName){
  return matchRowIterator(value,getColumnIndex(columnName));
}
