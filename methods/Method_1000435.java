public Object invoke(Connection conn,ResultSet rs,Sql sql) throws SQLException {
  List<String> list=new LinkedList<String>();
  while (rs.next())   list.add(rs.getString(1));
  return list.toArray(new String[list.size()]);
}
