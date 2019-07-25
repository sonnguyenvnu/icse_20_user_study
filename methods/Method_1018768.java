/** 
 * Moves the cursor to the first row of this ResultSet object initially, then subsequent calls move the cursor to the second row, the third row, and so on.
 * @return false when there are no more rows to read
 */
@Override public boolean next() throws SQLServerException {
  loggerExternal.entering(getClassNameLogging(),"next");
  if (loggerExternal.isLoggable(Level.FINER) && Util.isActivityTraceOn()) {
    loggerExternal.finer(toString() + " ActivityId: " + ActivityCorrelator.getNext().toString());
  }
  if (logger.isLoggable(java.util.logging.Level.FINER))   logger.finer(toString() + logCursorState());
  checkClosed();
  moverInit();
  if (AFTER_LAST_ROW == currentRow) {
    loggerExternal.exiting(getClassNameLogging(),"next",false);
    return false;
  }
  if (!isForwardOnly()) {
    if (BEFORE_FIRST_ROW == currentRow)     moveFirst();
 else     moveForward(1);
    boolean value=hasCurrentRow();
    loggerExternal.exiting(getClassNameLogging(),"next",value);
    return value;
  }
  if (0 != serverCursorId && maxRows > 0) {
    if (currentRow == maxRows) {
      currentRow=AFTER_LAST_ROW;
      loggerExternal.exiting(getClassNameLogging(),"next",false);
      return false;
    }
  }
  if (fetchBufferNext()) {
    if (BEFORE_FIRST_ROW == currentRow)     currentRow=1;
 else     updateCurrentRow(1);
    assert 0 == maxRows || currentRow <= maxRows;
    loggerExternal.exiting(getClassNameLogging(),"next",true);
    return true;
  }
  if (0 != serverCursorId) {
    doServerFetch(TDS.FETCH_NEXT,0,fetchSize);
    if (fetchBufferNext()) {
      if (BEFORE_FIRST_ROW == currentRow)       currentRow=1;
 else       updateCurrentRow(1);
      assert 0 == maxRows || currentRow <= maxRows;
      loggerExternal.exiting(getClassNameLogging(),"next",true);
      return true;
    }
  }
  if (UNKNOWN_ROW_COUNT == rowCount)   rowCount=currentRow;
  if (stmt.resultsReader().peekTokenType() == TDS.TDS_MSG) {
    stmt.startResults();
    stmt.getNextResult(false);
  }
  currentRow=AFTER_LAST_ROW;
  loggerExternal.exiting(getClassNameLogging(),"next",false);
  return false;
}
