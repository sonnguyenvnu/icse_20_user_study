protected List<Map<String,Object>> list(Class clz,String sql,List<Object> conditionList,Connection conn){
  sql=sql.replace("drop",SqlScript.SPACE).replace("delete",SqlScript.SPACE).replace("insert",SqlScript.SPACE).replace(";",SqlScript.SPACE);
  Parsed parsed=Parser.get(clz);
  sql=BeanUtilX.mapper(sql,parsed);
  sql=BeanUtilX.mapperForManu(sql,parsed);
  if (ConfigAdapter.isIsShowSql())   System.out.println(sql);
  List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
  PreparedStatement pstmt=null;
  try {
    conn.setAutoCommit(true);
    pstmt=conn.prepareStatement(sql);
    int i=1;
    if (conditionList != null) {
      for (      Object value : conditionList) {
        value=this.dialect.filterValue(value);
        this.dialect.setObject(i++,value,pstmt);
      }
    }
    ResultSet rs=pstmt.executeQuery();
    if (rs != null) {
      while (rs.next()) {
        Map<String,Object> mapR=new HashMap<String,Object>();
        list.add(mapR);
        ResultSetMetaData rsmd=rs.getMetaData();
        int count=rsmd.getColumnCount();
        for (i=1; i <= count; i++) {
          String key=rsmd.getColumnLabel(i);
          String value=rs.getString(i);
          String property=parsed.getProperty(key);
          if (StringUtil.isNullOrEmpty(property)) {
            property=key;
          }
          mapR.put(property,value);
        }
      }
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
 finally {
    close(pstmt);
    close(conn);
  }
  return list;
}
