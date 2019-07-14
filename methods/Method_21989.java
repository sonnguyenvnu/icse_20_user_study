private String buildOrder(final Collection<String> tableFields,final String sortName,final String sortOrder){
  if (Strings.isNullOrEmpty(sortName)) {
    return "";
  }
  String lowerUnderscore=CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,sortName);
  if (!tableFields.contains(lowerUnderscore)) {
    return "";
  }
  StringBuilder sqlBuilder=new StringBuilder();
  sqlBuilder.append(" ORDER BY ").append(lowerUnderscore);
switch (sortOrder.toUpperCase()) {
case "ASC":
    sqlBuilder.append(" ASC");
  break;
case "DESC":
sqlBuilder.append(" DESC");
break;
default :
sqlBuilder.append(" ASC");
}
return sqlBuilder.toString();
}
