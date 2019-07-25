@Override public int update(){
  if (!transactional) {
    session.commit(true);
  }
  Database db=session.getDatabase();
  boolean persistent=db.isPersistent();
  Table table=getSchema().findTableOrView(session,tableName);
  if (table == null) {
    if (ifTableExists) {
      return 0;
    }
    throw DbException.get(ErrorCode.TABLE_OR_VIEW_NOT_FOUND_1,tableName);
  }
  if (indexName != null && getSchema().findIndex(session,indexName) != null) {
    if (ifNotExists) {
      return 0;
    }
    throw DbException.get(ErrorCode.INDEX_ALREADY_EXISTS_1,indexName);
  }
  session.getUser().checkRight(table,Right.ALL);
  table.lock(session,true,true);
  if (!table.isPersistIndexes()) {
    persistent=false;
  }
  int id=getObjectId();
  if (indexName == null) {
    if (primaryKey) {
      indexName=table.getSchema().getUniqueIndexName(session,table,Constants.PREFIX_PRIMARY_KEY);
    }
 else {
      indexName=table.getSchema().getUniqueIndexName(session,table,Constants.PREFIX_INDEX);
    }
  }
  IndexType indexType;
  if (primaryKey) {
    if (table.findPrimaryKey() != null) {
      throw DbException.get(ErrorCode.SECOND_PRIMARY_KEY);
    }
    indexType=IndexType.createPrimaryKey(persistent,hash);
  }
 else   if (unique) {
    indexType=IndexType.createUnique(persistent,hash);
  }
 else   if (affinity) {
    indexType=IndexType.createAffinity();
  }
 else {
    indexType=IndexType.createNonUnique(persistent,hash,spatial);
  }
  IndexColumn.mapColumns(indexColumns,table);
  table.addIndex(session,indexName,id,indexColumns,indexType,create,comment);
  return 0;
}
