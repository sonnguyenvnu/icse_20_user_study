public void info() throws Exception {
  Connection conn=null;
  Class.forName("com.mysql.jdbc.Driver").newInstance();
  Map<String,Settings> groups=settings.getGroups(JPA.mode() + ".datasources");
  Settings mysqlSetting=groups.get("mysql");
  if (mysqlSetting == null) {
    return;
  }
  String url="jdbc:mysql://{}:{}/{}?useUnicode=true&characterEncoding=utf8";
  url=format(url,mysqlSetting.get("host","127.0.0.1"),mysqlSetting.get("port","3306"),mysqlSetting.get("database","csdn_search_client"));
  conn=DriverManager.getConnection(url,mysqlSetting.get("username"),mysqlSetting.get("password"));
  DatabaseMetaData databaseMetaData=conn.getMetaData();
  ResultSet resultSet=databaseMetaData.getTables(null,null,"%",null);
  while (resultSet.next()) {
    String tableName=resultSet.getString(3);
    tableNames.add(tableName);
    PreparedStatement ps=conn.prepareStatement("select * from " + tableName + " limit 1");
    ResultSet rs=ps.executeQuery();
    ResultSetMetaData rsme=rs.getMetaData();
    int columnCount=rsme.getColumnCount();
    Map<String,String> columns=new HashMap<String,String>();
    for (int i=1; i <= columnCount; i++) {
      String fieldName=rsme.getColumnName(i);
      String typeName=rsme.getColumnTypeName(i);
      columns.put(fieldName,typeName);
    }
    tableColumns.put(tableName,columns);
    tableColumns.put(Strings.toCamelCase(tableName,true),columns);
  }
  conn.close();
}
