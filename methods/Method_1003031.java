@Override public int update(){
  session.getUser().checkAdmin();
  session.commit(true);
  Database db=session.getDatabase();
  if (db.findRole(userName) != null) {
    throw DbException.get(ErrorCode.ROLE_ALREADY_EXISTS_1,userName);
  }
  if (db.findUser(userName) != null) {
    if (ifNotExists) {
      return 0;
    }
    throw DbException.get(ErrorCode.USER_ALREADY_EXISTS_1,userName);
  }
  int id=getObjectId();
  User user=new User(db,id,userName,false);
  user.setAdmin(admin);
  user.setComment(comment);
  if (hash != null && salt != null) {
    setSaltAndHash(user,session,salt,hash);
  }
 else   if (password != null) {
    setPassword(user,session,password);
  }
 else {
    throw DbException.throwInternalError();
  }
  db.addDatabaseObject(session,user);
  return 0;
}
