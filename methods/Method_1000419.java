public Object invoke(Connection conn,ResultSet rs,Sql sql) throws SQLException {
  if (null != rs && rs.next())   return new BlobValueAdaptor(Jdbcs.getFilePool()).get(rs,1);
  return null;
}
