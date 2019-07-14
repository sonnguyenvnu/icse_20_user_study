/** 
 * @nowebref
 */
public TableRow addRow(Object[] columnData){
  setRow(getRowCount(),columnData);
  return new RowPointer(this,rowCount - 1);
}
