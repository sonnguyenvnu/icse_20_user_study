@Override public int update(){
  if (!transactional) {
    session.commit(true);
  }
  session.getUser().checkAdmin();
  Database db=session.getDatabase();
  data.session=session;
  db.lockMeta(session);
  if (getSchema().findTableOrView(session,data.synonymName) != null) {
    throw DbException.get(ErrorCode.TABLE_OR_VIEW_ALREADY_EXISTS_1,data.synonymName);
  }
  if (data.synonymForSchema.findTableOrView(session,data.synonymFor) != null) {
    return createTableSynonym(db);
  }
  throw DbException.get(ErrorCode.TABLE_OR_VIEW_NOT_FOUND_1,data.synonymForSchema.getName() + "." + data.synonymFor);
}
