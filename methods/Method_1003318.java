/** 
 * Moves the cursor to the next row of the result set.
 * @return true if successful, false if there are no more rows
 */
@Override public boolean next() throws SQLException {
  if (source != null) {
    rowId++;
    currentRow=source.readRow();
    if (currentRow != null) {
      return true;
    }
  }
 else   if (rows != null && rowId < rows.size()) {
    rowId++;
    if (rowId < rows.size()) {
      currentRow=rows.get(rowId);
      return true;
    }
    currentRow=null;
  }
  if (autoClose) {
    close();
  }
  return false;
}
