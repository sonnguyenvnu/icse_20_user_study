@Override public int update(){
  session.getUser().checkSchemaAdmin();
  session.commit(true);
  Database db=session.getDatabase();
  Schema schema=db.findSchema(schemaName);
  if (schema == null) {
    if (!ifExists) {
      throw DbException.get(ErrorCode.SCHEMA_NOT_FOUND_1,schemaName);
    }
  }
 else {
    if (!schema.canDrop()) {
      throw DbException.get(ErrorCode.SCHEMA_CAN_NOT_BE_DROPPED_1,schemaName);
    }
    if (dropAction == ConstraintActionType.RESTRICT && !schema.isEmpty()) {
      ArrayList<SchemaObject> all=schema.getAll(null);
      int size=all.size();
      if (size > 0) {
        StringBuilder builder=new StringBuilder();
        for (int i=0; i < size; i++) {
          if (i > 0) {
            builder.append(", ");
          }
          builder.append(all.get(i).getName());
        }
        throw DbException.get(ErrorCode.CANNOT_DROP_2,schemaName,builder.toString());
      }
    }
    db.removeDatabaseObject(session,schema);
  }
  return 0;
}
