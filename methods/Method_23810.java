/** 
 * Make sure this is a legit row, and if not, expand the table. 
 */
protected void ensureRow(int row){
  if (row >= rowCount) {
    setRowCount(row + 1);
  }
}
