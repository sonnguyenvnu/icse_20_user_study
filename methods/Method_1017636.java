@Override public boolean next() throws SQLException {
  checkClosed();
  if (onInsertRow) {
    throw new PSQLException(GT.tr("Can''t use relative move methods while on the insert row."),PSQLState.INVALID_CURSOR_STATE);
  }
  if (currentRow + 1 >= rows.size()) {
    if (cursor == null || (maxRows > 0 && rowOffset + rows.size() >= maxRows)) {
      currentRow=rows.size();
      thisRow=null;
      rowBuffer=null;
      return false;
    }
    rowOffset+=rows.size();
    int fetchRows=fetchSize;
    if (maxRows != 0) {
      if (fetchRows == 0 || rowOffset + fetchRows > maxRows) {
        fetchRows=maxRows - rowOffset;
      }
    }
    connection.getQueryExecutor().fetch(cursor,new CursorResultHandler(),fetchRows);
    currentRow=0;
    if (rows.isEmpty()) {
      thisRow=null;
      rowBuffer=null;
      return false;
    }
  }
 else {
    currentRow++;
  }
  initRowBuffer();
  return true;
}
