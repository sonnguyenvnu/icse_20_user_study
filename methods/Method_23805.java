/** 
 * @webref table:method
 * @brief Trims whitespace from values
 * @see Table#removeTokens(String)
 */
public void trim(){
  columnTitles=PApplet.trim(columnTitles);
  for (int col=0; col < getColumnCount(); col++) {
    trim(col);
  }
  int lastColumn=getColumnCount() - 1;
  while (isEmptyArray(getStringColumn(lastColumn)) && lastColumn >= 0) {
    lastColumn--;
  }
  setColumnCount(lastColumn + 1);
  while (getColumnCount() > 0 && isEmptyArray(getStringColumn(0))) {
    removeColumn(0);
  }
  int lastRow=lastRowIndex();
  while (isEmptyArray(getStringRow(lastRow)) && lastRow >= 0) {
    lastRow--;
  }
  setRowCount(lastRow + 1);
  while (getRowCount() > 0 && isEmptyArray(getStringRow(0))) {
    removeRow(0);
  }
}
