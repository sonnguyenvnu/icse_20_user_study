/** 
 * Make sure this is a legit column, and if not, expand the table. 
 */
protected void ensureColumn(int col){
  if (col >= columns.length) {
    setColumnCount(col + 1);
  }
}
