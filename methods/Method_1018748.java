final void previous(SQLServerResultSet rs) throws SQLServerException {
  if (SQLServerResultSet.logger.isLoggable(java.util.logging.Level.FINER))   SQLServerResultSet.logger.finer(rs.toString() + logCursorState());
  assert 0 <= currentRow && currentRow <= maxRows + 1;
  if (0 == currentRow)   return;
  if (currentRow <= maxRows) {
    assert currentRow >= 1;
    updatedRow[currentRow - 1]=rs.getUpdatedCurrentRow();
    deletedRow[currentRow - 1]=rs.getDeletedCurrentRow();
    rowType[currentRow - 1]=rs.getCurrentRowType();
  }
  --currentRow;
  if (0 == currentRow)   return;
  assert null != rowMark[currentRow - 1];
  rs.fetchBufferReset(rowMark[currentRow - 1]);
  rs.setCurrentRowType(rowType[currentRow - 1]);
  rs.setUpdatedCurrentRow(updatedRow[currentRow - 1]);
  rs.setDeletedCurrentRow(deletedRow[currentRow - 1]);
}
