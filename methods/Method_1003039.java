@Override public int update(){
  session.commit(true);
  session.getUser().checkAdmin();
  TableSynonym synonym=getSchema().getSynonym(synonymName);
  if (synonym == null) {
    if (!ifExists) {
      throw DbException.get(ErrorCode.TABLE_OR_VIEW_NOT_FOUND_1,synonymName);
    }
  }
 else {
    session.getDatabase().removeSchemaObject(session,synonym);
  }
  return 0;
}
