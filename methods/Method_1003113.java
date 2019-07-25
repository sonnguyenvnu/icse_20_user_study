/** 
 * Moves the cursor to the last row, or row before first row if the current position is the first row.
 * @return true if there is a row available, false if not
 * @throws SQLException if the result set is closed
 */
@Override public boolean previous() throws SQLException {
  try {
    debugCodeCall("previous");
    checkClosed();
    return relative(-1);
  }
 catch (  Exception e) {
    throw logAndConvert(e);
  }
}
