/** 
 * Throw an error if this row doesn't exist. 
 */
protected void checkRow(int row){
  if (row < 0 || row >= rowCount) {
    throw new ArrayIndexOutOfBoundsException("Row " + row + " does not exist.");
  }
}
