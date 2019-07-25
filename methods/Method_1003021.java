@Override public int update(){
  session.commit(true);
  session.getUser().checkAdmin();
  Database db=session.getDatabase();
  if (db.findAggregate(name) != null || schema.findFunction(name) != null) {
    if (!ifNotExists) {
      throw DbException.get(ErrorCode.FUNCTION_ALIAS_ALREADY_EXISTS_1,name);
    }
  }
 else {
    int id=getObjectId();
    UserAggregate aggregate=new UserAggregate(db,id,name,javaClassMethod,force);
    db.addDatabaseObject(session,aggregate);
  }
  return 0;
}
