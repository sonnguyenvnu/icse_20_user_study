public List<Map> selectByParams(String sql,List params) throws SQLException {
  List<Map> list=new ArrayList<>();
  int index=1;
  pstmt=conn.prepareStatement(sql);
  if (null != params && !params.isEmpty()) {
    for (int i=0; i < params.size(); i++) {
      pstmt.setObject(index++,params.get(i));
    }
  }
  rs=pstmt.executeQuery();
  ResultSetMetaData metaData=rs.getMetaData();
  int colsLen=metaData.getColumnCount();
  while (rs.next()) {
    Map map=new HashMap(colsLen);
    for (int i=0; i < colsLen; i++) {
      String columnName=metaData.getColumnName(i + 1);
      Object columnValue=rs.getObject(columnName);
      if (null == columnValue) {
        columnValue="";
      }
      map.put(columnName,columnValue);
    }
    list.add(map);
  }
  return list;
}
