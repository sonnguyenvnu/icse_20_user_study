/** 
 * @param columnName title of the column to search
 */
public Iterator<TableRow> findRowIterator(String value,String columnName){
  return findRowIterator(value,getColumnIndex(columnName));
}
