@Override public int update(){
  session.commit(true);
  session.getUser().checkAdmin();
  Database db=session.getDatabase();
  if (getSchema().findFunction(aliasName) != null) {
    if (!ifNotExists) {
      throw DbException.get(ErrorCode.FUNCTION_ALIAS_ALREADY_EXISTS_1,aliasName);
    }
  }
 else {
    int id=getObjectId();
    FunctionAlias functionAlias;
    if (javaClassMethod != null) {
      functionAlias=FunctionAlias.newInstance(getSchema(),id,aliasName,javaClassMethod,force);
    }
 else {
      functionAlias=FunctionAlias.newInstanceFromSource(getSchema(),id,aliasName,source,force);
    }
    functionAlias.setDeterministic(deterministic);
    db.addSchemaObject(session,functionAlias);
  }
  return 0;
}
