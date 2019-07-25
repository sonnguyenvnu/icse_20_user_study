final boolean next(SQLServerResultSet rs) throws SQLServerException {
  if (SQLServerResultSet.logger.isLoggable(java.util.logging.Level.FINER))   SQLServerResultSet.logger.finer(rs.toString() + logCursorState());
  assert 0 <= currentRow && currentRow <= maxRows + 1;
  if (maxRows + 1 == currentRow)   return false;
  if (currentRow >= 1) {
    updatedRow[currentRow - 1]=rs.getUpdatedCurrentRow();
    deletedRow[currentRow - 1]=rs.getDeletedCurrentRow();
    rowType[currentRow - 1]=rs.getCurrentRowType();
  }
  ++currentRow;
  if (maxRows + 1 == currentRow) {
    rs.fetchBufferNext();
    return false;
  }
  if (null != rowMark[currentRow - 1]) {
    rs.fetchBufferReset(rowMark[currentRow - 1]);
    rs.setCurrentRowType(rowType[currentRow - 1]);
    rs.setUpdatedCurrentRow(updatedRow[currentRow - 1]);
    rs.setDeletedCurrentRow(deletedRow[currentRow - 1]);
    return true;
  }
  if (rs.fetchBufferNext()) {
    rowMark[currentRow - 1]=rs.fetchBufferMark();
    rowType[currentRow - 1]=rs.getCurrentRowType();
    if (SQLServerResultSet.logger.isLoggable(java.util.logging.Level.FINEST))     SQLServerResultSet.logger.finest(rs.toString() + " Set mark " + rowMark[currentRow - 1] + " for row " + currentRow + " of type " + rowType[currentRow - 1]);
    return true;
  }
  maxRows=currentRow - 1;
  return false;
}
