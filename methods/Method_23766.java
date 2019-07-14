/** 
 * @webref table:method
 * @brief Adds a row to a table
 * @see Table#removeRow(int)
 * @see Table#clearRows()
 */
public TableRow addRow(){
  setRowCount(rowCount + 1);
  return new RowPointer(this,rowCount - 1);
}
