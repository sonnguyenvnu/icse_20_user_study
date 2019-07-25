public Object invoke(Connection conn,ResultSet rs,Pojo pojo,Statement stmt) throws SQLException {
  if (null != rs && rs.next()) {
    return Record.create(rs);
  }
  return null;
}
