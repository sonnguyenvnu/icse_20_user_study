@Override public int update(){
  Table table=getSchema().resolveTableOrView(session,tableName);
  if (table == null) {
    if (ifTableExists) {
      return 0;
    }
    throw DbException.get(ErrorCode.TABLE_OR_VIEW_NOT_FOUND_1,tableName);
  }
  session.getUser().checkRight(table,Right.ALL);
  table.lock(session,true,true);
switch (type) {
case CommandInterface.ALTER_TABLE_SET_REFERENTIAL_INTEGRITY:
    table.setCheckForeignKeyConstraints(session,value,value ? checkExisting : false);
  break;
default :
DbException.throwInternalError("type=" + type);
}
return 0;
}
