@Override public int update(){
  session.commit(true);
  Database db=session.getDatabase();
  session.getUser().checkAdmin();
  if (getSchema().resolveTableOrView(session,tableName) != null) {
    if (ifNotExists) {
      return 0;
    }
    throw DbException.get(ErrorCode.TABLE_OR_VIEW_ALREADY_EXISTS_1,tableName);
  }
  int id=getObjectId();
  TableLink table=getSchema().createTableLink(id,tableName,driver,url,user,password,originalSchema,originalTable,emitUpdates,force);
  table.setTemporary(temporary);
  table.setGlobalTemporary(globalTemporary);
  table.setComment(comment);
  table.setReadOnly(readOnly);
  if (temporary && !globalTemporary) {
    session.addLocalTempTable(table);
  }
 else {
    db.addSchemaObject(session,table);
  }
  return 0;
}
