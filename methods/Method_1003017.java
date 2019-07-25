@Override public int update(){
  session.commit(true);
  Constraint constraint=getSchema().findConstraint(session,constraintName);
  if (constraint == null) {
    if (!ifExists) {
      throw DbException.get(ErrorCode.CONSTRAINT_NOT_FOUND_1,constraintName);
    }
  }
 else {
    session.getUser().checkRight(constraint.getTable(),Right.ALL);
    session.getUser().checkRight(constraint.getRefTable(),Right.ALL);
    session.getDatabase().removeSchemaObject(session,constraint);
  }
  return 0;
}
