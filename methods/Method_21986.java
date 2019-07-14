private String buildWhere(final String tableName,final Collection<String> tableFields,final Condition condition){
  StringBuilder sqlBuilder=new StringBuilder();
  sqlBuilder.append(" WHERE 1=1");
  if (null != condition.getFields() && !condition.getFields().isEmpty()) {
    for (    Map.Entry<String,Object> entry : condition.getFields().entrySet()) {
      String lowerUnderscore=CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,entry.getKey());
      if (null != entry.getValue() && tableFields.contains(lowerUnderscore)) {
        sqlBuilder.append(" AND ").append(lowerUnderscore).append("=?");
      }
    }
  }
  if (null != condition.getStartTime()) {
    sqlBuilder.append(" AND ").append(getTableTimeField(tableName)).append(">=?");
  }
  if (null != condition.getEndTime()) {
    sqlBuilder.append(" AND ").append(getTableTimeField(tableName)).append("<=?");
  }
  return sqlBuilder.toString();
}
