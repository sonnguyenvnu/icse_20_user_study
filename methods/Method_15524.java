@JSONField(serialize=false) @Override public String getTablePath(){
  String q=getQuote();
  String sqlTable=getSQLTable();
  String sch=getSQLSchema(sqlTable);
  return (StringUtil.isEmpty(sch,true) ? "" : q + sch + q + ".") + q + sqlTable + q + (isKeyPrefix() ? " AS " + getAlias() : "");
}
