@Override public int update(){
  targetTableFilter.startQuery(session);
  targetTableFilter.reset();
  Table table=targetTableFilter.getTable();
  session.getUser().checkRight(table,Right.DELETE);
  table.fire(session,Trigger.DELETE,true);
  table.lock(session,true,false);
  int limitRows=-1;
  if (limitExpr != null) {
    Value v=limitExpr.getValue(session);
    if (v != ValueNull.INSTANCE) {
      limitRows=v.getInt();
    }
  }
  try (RowList rows=new RowList(session)){
    setCurrentRowNumber(0);
    int count=0;
    while (limitRows != 0 && targetTableFilter.next()) {
      setCurrentRowNumber(rows.size() + 1);
      if (condition == null || condition.getBooleanValue(session)) {
        Row row=targetTableFilter.get();
        if (keysFilter == null || keysFilter.contains(row.getKey())) {
          if (table.isMVStore()) {
            Row lockedRow=table.lockRow(session,row);
            if (lockedRow == null) {
              continue;
            }
            if (!row.hasSharedData(lockedRow)) {
              row=lockedRow;
              targetTableFilter.set(row);
              if (condition != null && !condition.getBooleanValue(session)) {
                continue;
              }
            }
          }
          if (deltaChangeCollectionMode == ResultOption.OLD) {
            deltaChangeCollector.addRow(row.getValueList());
          }
          if (!table.fireRow() || !table.fireBeforeRow(session,row,null)) {
            rows.add(row);
          }
          count++;
          if (limitRows >= 0 && count >= limitRows) {
            break;
          }
        }
      }
    }
    int rowScanCount=0;
    for (rows.reset(); rows.hasNext(); ) {
      if ((++rowScanCount & 127) == 0) {
        checkCanceled();
      }
      Row row=rows.next();
      table.removeRow(session,row);
      session.log(table,UndoLogRecord.DELETE,row);
    }
    if (table.fireRow()) {
      for (rows.reset(); rows.hasNext(); ) {
        Row row=rows.next();
        table.fireAfterRow(session,row,null,false);
      }
    }
    table.fire(session,Trigger.DELETE,false);
    return count;
  }
 }
