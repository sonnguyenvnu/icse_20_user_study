private List<Map<String,Object>> executeQuery(final String sql,final Object... params){
  Connection connection=null;
  PreparedStatement ps=null;
  ResultSet rs=null;
  List<Map<String,Object>> list=null;
  try {
    connection=dataSource.getConnection();
    ps=connection.prepareStatement(sql);
    if (params != null) {
      for (int i=0; i < params.length; i++) {
        ps.setObject(i + 1,convertDataTypeToDB(params[i]));
      }
    }
    rs=ps.executeQuery();
    ResultSetMetaData md=rs.getMetaData();
    int columnCount=md.getColumnCount();
    list=new ArrayList<>();
    while (rs.next()) {
      Map<String,Object> rowData=Maps.newHashMap();
      for (int i=1; i <= columnCount; i++) {
        rowData.put(md.getColumnName(i),rs.getObject(i));
      }
      list.add(rowData);
    }
  }
 catch (  SQLException e) {
    LOGGER.error("executeQuery-> " + e.getMessage());
  }
 finally {
    close(connection,ps,rs);
  }
  return list;
}
