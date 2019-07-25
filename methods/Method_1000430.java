public Object invoke(Connection conn,ResultSet rs,Sql sql) throws SQLException {
  List<Boolean> list=new LinkedList<Boolean>();
  if (null != rs && rs.next())   list.add(rs.getBoolean(1));
  boolean[] array=new boolean[list.size()];
  for (int i=0; i < array.length; i++) {
    array[i]=list.get(i);
  }
  return array;
}
