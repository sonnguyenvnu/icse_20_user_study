/** 
 * Make sure this is a legit row and column. If not, expand the table. 
 */
protected void ensureBounds(int row,int col){
  ensureRow(row);
  ensureColumn(col);
}
