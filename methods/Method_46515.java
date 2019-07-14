public static StatementInfo delete(TableRecord tableRecord){
  String k=tableRecord.getTableName();
  FieldCluster v=tableRecord.getFieldCluster();
  StringBuilder rollbackSql=new StringBuilder(SqlUtils.INSERT).append(k).append('(');
  StringBuilder values=new StringBuilder();
  Object[] params=new Object[v.getFields().size()];
  int j=0;
  for (int i=0; i < v.getFields().size(); i++, j++) {
    FieldValue fieldValue=v.getFields().get(i);
    if (Objects.isNull(fieldValue.getValue())) {
      j--;
      continue;
    }
    params[i]=fieldValue.getValue();
    rollbackSql.append(fieldValue.getFieldName()).append(SqlUtils.SQL_COMMA_SEPARATOR);
    values.append("?, ");
  }
  SqlUtils.cutSuffix(SqlUtils.SQL_COMMA_SEPARATOR,rollbackSql);
  SqlUtils.cutSuffix(SqlUtils.SQL_COMMA_SEPARATOR,values);
  rollbackSql.append(") values(").append(values).append(')');
  return new StatementInfo(rollbackSql.toString(),Arrays.copyOf(params,j + 1));
}
