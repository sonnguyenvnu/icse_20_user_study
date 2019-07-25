@Override public ResultInterface query(int maxrows){
  session.getUser().checkAdmin();
  reset();
  Database db=session.getDatabase();
  if (schemaNames != null) {
    for (    String schemaName : schemaNames) {
      Schema schema=db.findSchema(schemaName);
      if (schema == null) {
        throw DbException.get(ErrorCode.SCHEMA_NOT_FOUND_1,schemaName);
      }
    }
  }
  try {
    result=createResult();
    deleteStore();
    openOutput();
    if (out != null) {
      buffer=new byte[Constants.IO_BUFFER_SIZE];
    }
    if (settings) {
      for (      Setting setting : db.getAllSettings()) {
        if (setting.getName().equals(SetTypes.getTypeName(SetTypes.CREATE_BUILD))) {
          continue;
        }
        add(setting.getCreateSQL(),false);
      }
    }
    if (out != null) {
      add("",true);
    }
    for (    User user : db.getAllUsers()) {
      add(user.getCreateSQL(passwords),false);
    }
    for (    Role role : db.getAllRoles()) {
      add(role.getCreateSQL(true),false);
    }
    for (    Schema schema : db.getAllSchemas()) {
      if (excludeSchema(schema)) {
        continue;
      }
      add(schema.getCreateSQL(),false);
    }
    for (    Domain datatype : db.getAllDomains()) {
      if (drop) {
        add(datatype.getDropSQL(),false);
      }
      add(datatype.getCreateSQL(),false);
    }
    for (    SchemaObject obj : db.getAllSchemaObjects(DbObject.CONSTANT)) {
      if (excludeSchema(obj.getSchema())) {
        continue;
      }
      Constant constant=(Constant)obj;
      add(constant.getCreateSQL(),false);
    }
    final ArrayList<Table> tables=db.getAllTablesAndViews(false);
    Collections.sort(tables,new Comparator<Table>(){
      @Override public int compare(      Table t1,      Table t2){
        return t1.getId() - t2.getId();
      }
    }
);
    for (    Table table : tables) {
      if (excludeSchema(table.getSchema())) {
        continue;
      }
      if (excludeTable(table)) {
        continue;
      }
      if (table.isHidden()) {
        continue;
      }
      table.lock(session,false,false);
      String sql=table.getCreateSQL();
      if (sql == null) {
        continue;
      }
      if (drop) {
        add(table.getDropSQL(),false);
      }
    }
    for (    SchemaObject obj : db.getAllSchemaObjects(DbObject.FUNCTION_ALIAS)) {
      if (excludeSchema(obj.getSchema())) {
        continue;
      }
      if (drop) {
        add(obj.getDropSQL(),false);
      }
      add(obj.getCreateSQL(),false);
    }
    for (    UserAggregate agg : db.getAllAggregates()) {
      if (drop) {
        add(agg.getDropSQL(),false);
      }
      add(agg.getCreateSQL(),false);
    }
    for (    SchemaObject obj : db.getAllSchemaObjects(DbObject.SEQUENCE)) {
      if (excludeSchema(obj.getSchema())) {
        continue;
      }
      Sequence sequence=(Sequence)obj;
      if (drop) {
        add(sequence.getDropSQL(),false);
      }
      add(sequence.getCreateSQL(),false);
    }
    int count=0;
    for (    Table table : tables) {
      if (excludeSchema(table.getSchema())) {
        continue;
      }
      if (excludeTable(table)) {
        continue;
      }
      if (table.isHidden()) {
        continue;
      }
      table.lock(session,false,false);
      String createTableSql=table.getCreateSQL();
      if (createTableSql == null) {
        continue;
      }
      final TableType tableType=table.getTableType();
      add(createTableSql,false);
      final ArrayList<Constraint> constraints=table.getConstraints();
      if (constraints != null) {
        for (        Constraint constraint : constraints) {
          if (Constraint.Type.PRIMARY_KEY == constraint.getConstraintType()) {
            add(constraint.getCreateSQLWithoutIndexes(),false);
          }
        }
      }
      if (TableType.TABLE == tableType) {
        if (table.canGetRowCount()) {
          StringBuilder builder=new StringBuilder("-- ").append(table.getRowCountApproximation()).append(" +/- SELECT COUNT(*) FROM ");
          table.getSQL(builder,false);
          add(builder.toString(),false);
        }
        if (data) {
          count=generateInsertValues(count,table);
        }
      }
      final ArrayList<Index> indexes=table.getIndexes();
      for (int j=0; indexes != null && j < indexes.size(); j++) {
        Index index=indexes.get(j);
        if (!index.getIndexType().getBelongsToConstraint()) {
          add(index.getCreateSQL(),false);
        }
      }
    }
    if (tempLobTableCreated) {
      add("DROP TABLE IF EXISTS SYSTEM_LOB_STREAM",true);
      add("CALL SYSTEM_COMBINE_BLOB(-1)",true);
      add("DROP ALIAS IF EXISTS SYSTEM_COMBINE_CLOB",true);
      add("DROP ALIAS IF EXISTS SYSTEM_COMBINE_BLOB",true);
      tempLobTableCreated=false;
    }
    final ArrayList<SchemaObject> constraints=db.getAllSchemaObjects(DbObject.CONSTRAINT);
    Collections.sort(constraints,null);
    for (    SchemaObject obj : constraints) {
      if (excludeSchema(obj.getSchema())) {
        continue;
      }
      Constraint constraint=(Constraint)obj;
      if (excludeTable(constraint.getTable())) {
        continue;
      }
      if (constraint.getTable().isHidden()) {
        continue;
      }
      if (Constraint.Type.PRIMARY_KEY != constraint.getConstraintType()) {
        add(constraint.getCreateSQLWithoutIndexes(),false);
      }
    }
    for (    SchemaObject obj : db.getAllSchemaObjects(DbObject.TRIGGER)) {
      if (excludeSchema(obj.getSchema())) {
        continue;
      }
      TriggerObject trigger=(TriggerObject)obj;
      if (excludeTable(trigger.getTable())) {
        continue;
      }
      add(trigger.getCreateSQL(),false);
    }
    for (    Right right : db.getAllRights()) {
      DbObject object=right.getGrantedObject();
      if (object != null) {
        if (object instanceof Schema) {
          if (excludeSchema((Schema)object)) {
            continue;
          }
        }
 else         if (object instanceof Table) {
          Table table=(Table)object;
          if (excludeSchema(table.getSchema())) {
            continue;
          }
          if (excludeTable(table)) {
            continue;
          }
        }
      }
      add(right.getCreateSQL(),false);
    }
    for (    Comment comment : db.getAllComments()) {
      add(comment.getCreateSQL(),false);
    }
    if (out != null) {
      out.close();
    }
  }
 catch (  IOException e) {
    throw DbException.convertIOException(e,getFileName());
  }
 finally {
    closeIO();
  }
  result.done();
  LocalResult r=result;
  reset();
  return r;
}
