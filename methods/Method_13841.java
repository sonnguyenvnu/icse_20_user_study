@Override public List<Object> getNextRowOfCells() throws IOException {
  try {
    if (!usedHeaders) {
      List<Object> row=new ArrayList<Object>(dbColumns.size());
      for (      DatabaseColumn cd : dbColumns) {
        row.add(cd.getName());
      }
      usedHeaders=true;
      return row;
    }
    if (rowsOfCells == null || (nextRow >= batchRowStart + rowsOfCells.size() && !end)) {
      int newBatchRowStart=batchRowStart + (rowsOfCells == null ? 0 : rowsOfCells.size());
      rowsOfCells=getRowsOfCells(newBatchRowStart);
      processedRows=processedRows + rowsOfCells.size();
      batchRowStart=newBatchRowStart;
      setProgress(job,querySource,-1);
    }
    if (rowsOfCells != null && nextRow - batchRowStart < rowsOfCells.size()) {
      List<Object> result=rowsOfCells.get(nextRow++ - batchRowStart);
      if (nextRow >= batchSize) {
        rowsOfCells=getRowsOfCells(processedRows);
        processedRows=processedRows + rowsOfCells.size();
        if (logger.isDebugEnabled()) {
          logger.debug("[[ Returning last row in batch:nextRow::{}, processedRows:{} ]]",nextRow,processedRows);
        }
        nextRow=0;
        if (processedRows % 100 == 0) {
          setProgress(job,querySource,progress++);
        }
        if (processedRows % 10000 == 0) {
          if (logger.isDebugEnabled()) {
            logger.debug("[[ {} rows processed... ]]",processedRows);
          }
        }
      }
      return result;
    }
 else {
      if (logger.isDebugEnabled()) {
        logger.debug("[[processedRows:{} ]]",processedRows);
      }
      return null;
    }
  }
 catch (  DatabaseServiceException e) {
    logger.error("DatabaseServiceException::{}",e);
    throw new IOException(e);
  }
}
