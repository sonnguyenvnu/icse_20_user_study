/** 
 * Updates an existing row or inserts a new one.
 * @param row row to replace
 * @return 1 if row was inserted, 1 if row was updated by a MERGE statement,and 2 if row was updated by a REPLACE statement
 */
private int merge(Row row){
  int count;
  if (update == null) {
    count=0;
  }
 else {
    ArrayList<Parameter> k=update.getParameters();
    for (int i=0; i < columns.length; i++) {
      Column col=columns[i];
      Value v=row.getValue(col.getColumnId());
      Parameter p=k.get(i);
      p.setValue(v);
    }
    for (int i=0; i < keys.length; i++) {
      Column col=keys[i];
      Value v=row.getValue(col.getColumnId());
      if (v == null) {
        throw DbException.get(ErrorCode.COLUMN_CONTAINS_NULL_VALUES_1,col.getSQL(false));
      }
      Parameter p=k.get(columns.length + i);
      p.setValue(v);
    }
    count=update.update();
  }
  if (count == 0) {
    try {
      table.validateConvertUpdateSequence(session,row);
      if (deltaChangeCollectionMode == ResultOption.NEW) {
        deltaChangeCollector.addRow(row.getValueList().clone());
      }
      boolean done=table.fireBeforeRow(session,null,row);
      if (!done) {
        table.lock(session,true,false);
        table.addRow(session,row);
        if (deltaChangeCollectionMode == ResultOption.FINAL) {
          deltaChangeCollector.addRow(row.getValueList());
        }
        session.log(table,UndoLogRecord.INSERT,row);
        table.fireAfterRow(session,null,row,false);
      }
      return 1;
    }
 catch (    DbException e) {
      if (e.getErrorCode() == ErrorCode.DUPLICATE_KEY_1) {
        Index index=(Index)e.getSource();
        if (index != null) {
          Column[] indexColumns;
          if (index instanceof MVPrimaryIndex) {
            MVPrimaryIndex foundMV=(MVPrimaryIndex)index;
            indexColumns=new Column[]{foundMV.getIndexColumns()[foundMV.getMainIndexColumn()].column};
          }
 else {
            indexColumns=index.getColumns();
          }
          boolean indexMatchesKeys;
          if (indexColumns.length <= keys.length) {
            indexMatchesKeys=true;
            for (int i=0; i < indexColumns.length; i++) {
              if (indexColumns[i] != keys[i]) {
                indexMatchesKeys=false;
                break;
              }
            }
          }
 else {
            indexMatchesKeys=false;
          }
          if (indexMatchesKeys) {
            throw DbException.get(ErrorCode.CONCURRENT_UPDATE_1,table.getName());
          }
        }
      }
      throw e;
    }
  }
 else   if (count == 1) {
    return isReplace ? 2 : 1;
  }
  throw DbException.get(ErrorCode.DUPLICATE_KEY_1,table.getSQL(false));
}
