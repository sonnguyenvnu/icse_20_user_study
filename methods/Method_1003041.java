@Override public int update(){
  session.getUser().checkAdmin();
  session.commit(true);
  Database db=session.getDatabase();
  if (roleNames != null) {
    for (    String name : roleNames) {
      Role grantedRole=db.findRole(name);
      if (grantedRole == null) {
        throw DbException.get(ErrorCode.ROLE_NOT_FOUND_1,name);
      }
      if (operationType == CommandInterface.GRANT) {
        grantRole(grantedRole);
      }
 else       if (operationType == CommandInterface.REVOKE) {
        revokeRole(grantedRole);
      }
 else {
        DbException.throwInternalError("type=" + operationType);
      }
    }
  }
 else {
    if (operationType == CommandInterface.GRANT) {
      grantRight();
    }
 else     if (operationType == CommandInterface.REVOKE) {
      revokeRight();
    }
 else {
      DbException.throwInternalError("type=" + operationType);
    }
  }
  return 0;
}
