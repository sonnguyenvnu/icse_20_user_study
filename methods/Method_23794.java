/** 
 * @webref table:method
 * @brief Finds a row that matches the given expression
 * @param regexp the regular expression to match
 * @param column ID number of the column to search
 * @see Table#getRow(int)
 * @see Table#rows()
 * @see Table#findRow(String,int)
 * @see Table#findRows(String,int)
 * @see Table#matchRows(String,int)
 */
public TableRow matchRow(String regexp,int column){
  int row=matchRowIndex(regexp,column);
  return (row == -1) ? null : new RowPointer(this,row);
}
