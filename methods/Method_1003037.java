@Override public int update(){
  session.getUser().checkAdmin();
  session.commit(true);
  Database db=session.getDatabase();
  if (roleName.equals(Constants.PUBLIC_ROLE_NAME)) {
    throw DbException.get(ErrorCode.ROLE_CAN_NOT_BE_DROPPED_1,roleName);
  }
  Role role=db.findRole(roleName);
  if (role == null) {
    if (!ifExists) {
      throw DbException.get(ErrorCode.ROLE_NOT_FOUND_1,roleName);
    }
  }
 else {
    db.removeDatabaseObject(session,role);
  }
  return 0;
}
