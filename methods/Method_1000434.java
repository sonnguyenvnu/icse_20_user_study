public Object invoke(Connection conn,ResultSet rs,Sql sql) throws SQLException {
  final ResultSetMetaData meta=rs.getMetaData();
  ResultSetLooping ing=new ResultSetLooping(){
    protected boolean createObject(    int index,    ResultSet rs,    SqlContext context,    int rowCout){
      NutMap re=new NutMap();
      Record.create(re,rs,meta);
      list.add(re);
      return true;
    }
  }
;
  ing.doLoop(rs,sql.getContext());
  return ing.getList();
}
