@Override public int update(){
  session.getUser().checkAdmin();
  session.commit(true);
  Database db=session.getDatabase();
  session.getUser().checkAdmin();
  if (db.findDomain(typeName) != null) {
    if (ifNotExists) {
      return 0;
    }
    throw DbException.get(ErrorCode.DOMAIN_ALREADY_EXISTS_1,typeName);
  }
  DataType builtIn=DataType.getTypeByName(typeName,session.getDatabase().getMode());
  if (builtIn != null) {
    if (!builtIn.hidden) {
      throw DbException.get(ErrorCode.DOMAIN_ALREADY_EXISTS_1,typeName);
    }
    Table table=session.getDatabase().getFirstUserTable();
    if (table != null) {
      StringBuilder builder=new StringBuilder(typeName).append(" (");
      table.getSQL(builder,false).append(')');
      throw DbException.get(ErrorCode.DOMAIN_ALREADY_EXISTS_1,builder.toString());
    }
  }
  int id=getObjectId();
  Domain type=new Domain(db,id,typeName);
  type.setColumn(column);
  db.addDatabaseObject(session,type);
  return 0;
}
