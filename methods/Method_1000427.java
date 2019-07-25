public Object invoke(Connection conn,ResultSet rs,Sql sql) throws SQLException {
  if (null != rs && rs.next()) {
    return Record.create(rs);
  }
  return null;
}
