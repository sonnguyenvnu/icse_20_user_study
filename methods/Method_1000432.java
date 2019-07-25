public Object invoke(Connection conn,ResultSet rs,Sql sql) throws SQLException {
  LinkedIntArray ary=new LinkedIntArray(20);
  while (rs.next())   ary.push(rs.getInt(1));
  return ary.toArray();
}
