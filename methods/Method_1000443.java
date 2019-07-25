public Object invoke(Connection conn,ResultSet rs,Pojo pojo,Statement stmt) throws SQLException {
  if (null != rs && rs.next())   return pojo.getEntity().getObject(rs,pojo.getContext().getFieldMatcher(),prefix);
  return null;
}
