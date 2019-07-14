private String buildSelectQuery(){
  return String.format("select %s from %s ",StringUtils.join(getColumnNames(),", "),getTableName());
}
