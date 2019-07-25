@Override public int update(){
  session.commit(true);
  Constraint constraint=getSchema().findConstraint(session,constraintName);
  if (constraint == null) {
    throw DbException.get(ErrorCode.CONSTRAINT_NOT_FOUND_1,constraintName);
  }
  if (getSchema().findConstraint(session,newConstraintName) != null || newConstraintName.equals(constraintName)) {
    throw DbException.get(ErrorCode.CONSTRAINT_ALREADY_EXISTS_1,newConstraintName);
  }
  session.getUser().checkRight(constraint.getTable(),Right.ALL);
  session.getUser().checkRight(constraint.getRefTable(),Right.ALL);
  session.getDatabase().renameSchemaObject(session,constraint,newConstraintName);
  return 0;
}
