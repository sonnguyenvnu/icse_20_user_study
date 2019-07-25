@Override public int update(){
  session.getUser().checkAdmin();
  session.commit(true);
  Database db=session.getDatabase();
  Constant constant=getSchema().findConstant(constantName);
  if (constant == null) {
    if (!ifExists) {
      throw DbException.get(ErrorCode.CONSTANT_NOT_FOUND_1,constantName);
    }
  }
 else {
    db.removeSchemaObject(session,constant);
  }
  return 0;
}
