/** 
 * @webref table:method
 * @brief Gets all values in the specified column
 * @param columnName title of the column to search
 * @see Table#getInt(int,int)
 * @see Table#getFloat(int,int)
 * @see Table#getString(int,int)
 * @see Table#setInt(int,int,int)
 * @see Table#setFloat(int,int,float)
 * @see Table#setString(int,int,String)
 */
public String[] getStringColumn(String columnName){
  int col=getColumnIndex(columnName);
  return (col == -1) ? null : getStringColumn(col);
}
