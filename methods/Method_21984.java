private String buildSelectCount(final String tableName){
  return String.format("SELECT COUNT(1) FROM %s",tableName);
}
