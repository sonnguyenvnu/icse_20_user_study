@Override public int update(){
  session.commit(true);
  Database db=session.getDatabase();
  Index index=getSchema().findIndex(session,indexName);
  if (index == null) {
    if (!ifExists) {
      throw DbException.get(ErrorCode.INDEX_NOT_FOUND_1,indexName);
    }
  }
 else {
    Table table=index.getTable();
    session.getUser().checkRight(index.getTable(),Right.ALL);
    Constraint pkConstraint=null;
    ArrayList<Constraint> constraints=table.getConstraints();
    for (int i=0; constraints != null && i < constraints.size(); i++) {
      Constraint cons=constraints.get(i);
      if (cons.usesIndex(index)) {
        if (Constraint.Type.PRIMARY_KEY == cons.getConstraintType()) {
          pkConstraint=cons;
        }
 else {
          throw DbException.get(ErrorCode.INDEX_BELONGS_TO_CONSTRAINT_2,indexName,cons.getName());
        }
      }
    }
    index.getTable().setModified();
    if (pkConstraint != null) {
      db.removeSchemaObject(session,pkConstraint);
    }
 else {
      db.removeSchemaObject(session,index);
    }
  }
  return 0;
}
