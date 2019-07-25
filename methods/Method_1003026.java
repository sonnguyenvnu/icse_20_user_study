@Override public int update(){
  session.getUser().checkAdmin();
  session.commit(true);
  Database db=session.getDatabase();
  if (db.findUser(roleName) != null) {
    throw DbException.get(ErrorCode.USER_ALREADY_EXISTS_1,roleName);
  }
  if (db.findRole(roleName) != null) {
    if (ifNotExists) {
      return 0;
    }
    throw DbException.get(ErrorCode.ROLE_ALREADY_EXISTS_1,roleName);
  }
  int id=getObjectId();
  Role role=new Role(db,id,roleName,false);
  db.addDatabaseObject(session,role);
  return 0;
}
