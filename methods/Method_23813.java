/** 
 * Throw an error if this column doesn't exist. 
 */
protected void checkColumn(int column){
  if (column < 0 || column >= columns.length) {
    throw new ArrayIndexOutOfBoundsException("Column " + column + " does not exist.");
  }
}
