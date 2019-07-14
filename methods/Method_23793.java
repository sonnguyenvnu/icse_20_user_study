/** 
 * Return a list of rows that match the regex passed in. If there are no matches, a zero length array will be returned (not a null array).
 * @param what the String to match
 * @param columnName title of the column to search
 */
public int[] matchRowIndices(String what,String columnName){
  return matchRowIndices(what,getColumnIndex(columnName));
}
