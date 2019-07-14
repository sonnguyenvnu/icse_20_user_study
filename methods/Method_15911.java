@Override public SqlAppender accept(String wherePrefix,Term term,RDBColumnMetaData column,String tableAlias){
  if (!support(column)) {
    return buildNotSupport(wherePrefix,term,column,tableAlias);
  }
  ChangedTermValue changedValue=createChangedTermValue(term);
  boolean any=term.getOptions().contains("any");
  List<Object> list=BoostTermTypeMapper.convertList(column,changedValue.getOld());
  EnumDict[] dicts=getAllOption(column).stream().filter(d -> d.eq(list)).toArray(EnumDict[]::new);
  changedValue.setValue(EnumDict.toMask(dicts));
  Dialect dialect=column.getTableMetaData().getDatabaseMetaData().getDialect();
  String columnName=dialect.buildColumnName(tableAlias,column.getName());
  String where="#{" + wherePrefix + ".value.value}";
  SqlFunction sqlFunction=dialect.getFunction(SqlFunction.bitand);
  if (sqlFunction == null) {
    throw new UnsupportedOperationException("??????[BITAND]??");
  }
  String bitAnd=sqlFunction.apply(SqlFunction.Param.of(RenderPhase.where,Arrays.asList(columnName,where)));
  String n;
  if (any) {
    n=not ? "=" : "!=";
  }
 else {
    n=not ? "!=" : "=";
  }
  return new SqlAppender().add(bitAnd,n,any ? "0" : columnName);
}
