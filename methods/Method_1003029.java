@Override public int update(){
  if (!transactional) {
    session.commit(true);
  }
  Database db=session.getDatabase();
  if (!db.isPersistent()) {
    data.persistIndexes=false;
  }
  boolean isSessionTemporary=data.temporary && !data.globalTemporary;
  if (!isSessionTemporary) {
    db.lockMeta(session);
  }
  if (getSchema().resolveTableOrView(session,data.tableName) != null) {
    if (ifNotExists) {
      return 0;
    }
    throw DbException.get(ErrorCode.TABLE_OR_VIEW_ALREADY_EXISTS_1,data.tableName);
  }
  if (asQuery != null) {
    asQuery.prepare();
    if (data.columns.isEmpty()) {
      generateColumnsFromQuery();
    }
 else     if (data.columns.size() != asQuery.getColumnCount()) {
      throw DbException.get(ErrorCode.COLUMN_COUNT_DOES_NOT_MATCH);
    }
 else {
      ArrayList<Column> columns=data.columns;
      for (int i=0; i < columns.size(); i++) {
        Column column=columns.get(i);
        if (column.getType().getValueType() == Value.UNKNOWN) {
          columns.set(i,new Column(column.getName(),asQuery.getExpressions().get(i).getType()));
        }
      }
    }
  }
  changePrimaryKeysToNotNull(data.columns);
  data.id=getObjectId();
  data.create=create;
  data.session=session;
  Table table=getSchema().createTable(data);
  ArrayList<Sequence> sequences=generateSequences(data.columns,data.temporary);
  table.setComment(comment);
  if (isSessionTemporary) {
    if (onCommitDrop) {
      table.setOnCommitDrop(true);
    }
    if (onCommitTruncate) {
      table.setOnCommitTruncate(true);
    }
    session.addLocalTempTable(table);
  }
 else {
    db.lockMeta(session);
    db.addSchemaObject(session,table);
  }
  try {
    for (    Column c : data.columns) {
      c.prepareExpression(session);
    }
    for (    Sequence sequence : sequences) {
      table.addSequence(sequence);
    }
    createConstraints();
    if (asQuery != null && !withNoData) {
      boolean old=session.isUndoLogEnabled();
      try {
        session.setUndoLogEnabled(false);
        session.startStatementWithinTransaction();
        Insert insert=new Insert(session);
        insert.setSortedInsertMode(sortedInsertMode);
        insert.setQuery(asQuery);
        insert.setTable(table);
        insert.setInsertFromSelect(true);
        insert.prepare();
        insert.update();
      }
  finally {
        session.setUndoLogEnabled(old);
      }
    }
    HashSet<DbObject> set=new HashSet<>();
    table.addDependencies(set);
    for (    DbObject obj : set) {
      if (obj == table) {
        continue;
      }
      if (obj.getType() == DbObject.TABLE_OR_VIEW) {
        if (obj instanceof Table) {
          Table t=(Table)obj;
          if (t.getId() > table.getId()) {
            throw DbException.get(ErrorCode.FEATURE_NOT_SUPPORTED_1,"Table depends on another table " + "with a higher ID: " + t + ", this is currently not supported, " + "as it would prevent the database from " + "being re-opened");
          }
        }
      }
    }
  }
 catch (  DbException e) {
    try {
      db.checkPowerOff();
      db.removeSchemaObject(session,table);
      if (!transactional) {
        session.commit(true);
      }
    }
 catch (    Throwable ex) {
      e.addSuppressed(ex);
    }
    throw e;
  }
  return 0;
}
