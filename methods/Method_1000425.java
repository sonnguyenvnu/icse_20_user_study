public Object invoke(Connection conn,ResultSet rs,Sql sql) throws SQLException {
  if (null != rs && rs.next())   return rs.getLong(1);
  return null;
}
