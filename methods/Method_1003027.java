@Override public int update(){
  session.commit(true);
  Database db=session.getDatabase();
  if (getSchema().findSequence(sequenceName) != null) {
    if (ifNotExists) {
      return 0;
    }
    throw DbException.get(ErrorCode.SEQUENCE_ALREADY_EXISTS_1,sequenceName);
  }
  int id=getObjectId();
  Sequence sequence=new Sequence(session,getSchema(),id,sequenceName,options,belongsToTable);
  db.addSchemaObject(session,sequence);
  return 0;
}
