private String buildCountSql(final String tableName,final Collection<String> tableFields,final Condition condition){
  StringBuilder sqlBuilder=new StringBuilder();
  String selectSql=buildSelectCount(tableName);
  String whereSql=buildWhere(tableName,tableFields,condition);
  sqlBuilder.append(selectSql).append(whereSql);
  return sqlBuilder.toString();
}
