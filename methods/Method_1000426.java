public Object invoke(Connection conn,ResultSet rs,Sql sql) throws SQLException {
  if (null != rs && rs.next()) {
    NutMap re=new NutMap();
    Record.create(re,rs,null);
    return re;
  }
  return null;
}
