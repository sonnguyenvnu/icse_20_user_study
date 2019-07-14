/** 
 * Return a list of rows that contain the String passed in. If there are no matches, a zero length array will be returned (not a null array).
 * @param value the String to match
 * @param columnName title of the column to search
 */
public int[] findRowIndices(String value,String columnName){
  return findRowIndices(value,getColumnIndex(columnName));
}
