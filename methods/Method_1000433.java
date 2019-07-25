public Object invoke(Connection conn,ResultSet rs,Sql sql) throws SQLException {
  LinkedLongArray ary=new LinkedLongArray(20);
  while (rs.next())   ary.push(rs.getLong(1));
  return ary.toArray();
}
