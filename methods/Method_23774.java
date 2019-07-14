/** 
 * @webref table:method
 * @brief Store an integer value in the specified row and column
 * @param row ID number of the target row
 * @param column ID number of the target column
 * @param value value to assign
 * @see Table#setFloat(int,int,float)
 * @see Table#setString(int,int,String)
 * @see Table#getInt(int,int)
 * @see Table#getFloat(int,int)
 * @see Table#getString(int,int)
 * @see Table#getStringColumn(String)
 */
public void setInt(int row,int column,int value){
  if (columnTypes[column] == STRING) {
    setString(row,column,String.valueOf(value));
  }
 else {
    ensureBounds(row,column);
    if (columnTypes[column] != INT && columnTypes[column] != CATEGORY) {
      throw new IllegalArgumentException("Column " + column + " is not an int column.");
    }
    int[] intData=(int[])columns[column];
    intData[row]=value;
  }
}
