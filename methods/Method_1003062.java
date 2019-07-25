private Prepared prepare(Session session,String sql,ConstraintActionType action){
  Prepared command=session.prepare(sql);
  if (action != ConstraintActionType.CASCADE) {
    ArrayList<Parameter> params=command.getParameters();
    for (int i=0, len=columns.length; i < len; i++) {
      Column column=columns[i].column;
      Parameter param=params.get(i);
      Value value;
      if (action == ConstraintActionType.SET_NULL) {
        value=ValueNull.INSTANCE;
      }
 else {
        Expression expr=column.getDefaultExpression();
        if (expr == null) {
          throw DbException.get(ErrorCode.NO_DEFAULT_SET_1,column.getName());
        }
        value=expr.getValue(session);
      }
      param.setValue(value);
    }
  }
  return command;
}
