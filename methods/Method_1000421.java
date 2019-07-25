public Object invoke(Connection conn,ResultSet rs,Sql sql) throws SQLException {
  if (null != rs && rs.next())   return rs.getDouble(1);
  return null;
}
