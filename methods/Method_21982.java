private String buildDataSql(final String tableName,final Collection<String> tableFields,final Condition condition){
  StringBuilder sqlBuilder=new StringBuilder();
  String selectSql=buildSelect(tableName,tableFields);
  String whereSql=buildWhere(tableName,tableFields,condition);
  String orderSql=buildOrder(tableFields,condition.getSort(),condition.getOrder());
  String limitSql=buildLimit(condition.getPage(),condition.getPerPage());
  sqlBuilder.append(selectSql).append(whereSql).append(orderSql).append(limitSql);
  return sqlBuilder.toString();
}
