public Object invoke(Connection conn,ResultSet rs,Pojo pojo,Statement stmt) throws SQLException {
  if (null != rs && rs.next()) {
    return rs.getObject(1);
  }
  return null;
}
