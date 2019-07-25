public Object invoke(Connection conn,ResultSet rs,final Pojo pojo,Statement stmt) throws SQLException {
  ResultSetLooping ing=new ResultSetLooping(){
    protected boolean createObject(    int index,    ResultSet rs,    SqlContext context,    int rowCount){
      list.add(pojo.getEntity().getObject(rs,context.getFieldMatcher(),prefix));
      return true;
    }
  }
;
  ing.doLoop(rs,pojo.getContext());
  return ing.getList();
}
