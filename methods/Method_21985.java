private String buildSelect(final String tableName,final Collection<String> tableFields){
  StringBuilder sqlBuilder=new StringBuilder();
  sqlBuilder.append("SELECT ");
  for (  String each : tableFields) {
    sqlBuilder.append(each).append(",");
  }
  sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
  sqlBuilder.append(" FROM ").append(tableName);
  return sqlBuilder.toString();
}
