/** 
 * Returns a <code>Map<String, Object></code> of properties stored in the database
 * @throws Exception
 */
synchronized Map<String,Object> load() throws Exception {
  Map<String,Object> map=new HashMap<String,Object>();
  Connection conn=null;
  PreparedStatement pstmt=null;
  ResultSet rs=null;
  try {
    conn=getConnection();
    pstmt=conn.prepareStatement(query.toString());
    rs=pstmt.executeQuery();
    while (rs.next()) {
      String key=(String)rs.getObject(keyColumnName);
      Object value=rs.getObject(valueColumnName);
      map.put(key,value);
    }
  }
 catch (  SQLException e) {
    throw e;
  }
 finally {
    close(conn,pstmt,rs);
  }
  return map;
}
