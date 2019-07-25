public Object invoke(Connection conn,ResultSet rs,Pojo pojo,Statement stmt) throws SQLException {
  ResultSetLooping ing=new ResultSetLooping(){
    protected boolean createObject(    int index,    ResultSet rs,    SqlContext context,    int rowCount){
      list.add(Record.create(rs));
      return true;
    }
  }
;
  ing.doLoop(rs,pojo.getContext());
  return ing.getList();
}
