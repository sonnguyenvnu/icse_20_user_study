private MethodField parseSQLCastExprInWhere(SQLCastExpr soExpr) throws SqlParseException {
  MethodField methodField=FieldMaker.makeMethodField("cast",Collections.singletonList(soExpr),null,null,query != null ? query.getFrom().getAlias() : null,true);
  List<KVValue> params=methodField.getParams();
  KVValue param=params.get(0);
  params.clear();
  params.add(new KVValue(param.key));
  params.add(new KVValue(param.value));
  return methodField;
}
