@Override public int update(){
  session.getUser().checkAdmin();
  session.commit(true);
  Database db=session.getDatabase();
  UserAggregate aggregate=db.findAggregate(name);
  if (aggregate == null) {
    if (!ifExists) {
      throw DbException.get(ErrorCode.AGGREGATE_NOT_FOUND_1,name);
    }
  }
 else {
    db.removeDatabaseObject(session,aggregate);
  }
  return 0;
}
