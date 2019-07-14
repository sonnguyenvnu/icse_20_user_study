private List<SqlInfo> parseSql(String sqlText,String datasourceId){
  List<String> sqlList=Sqls.parse(sqlText);
  return sqlList.stream().map(sql -> {
    SqlInfo sqlInfo=new SqlInfo();
    sqlInfo.setSql(sql);
    sqlInfo.setDatasourceId(datasourceId);
    sqlInfo.setType(sql.split("[ ]")[0].toLowerCase());
    return sqlInfo;
  }
).collect(Collectors.toList());
}
