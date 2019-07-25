@Override protected Object process(final ResultSet rs,final Entity<?> entity,final SqlContext context) throws SQLException {
  ResultSetLooping ing=new ResultSetLooping(){
    protected boolean createObject(    int index,    ResultSet rs,    SqlContext context,    int rowCount){
      list.add(entity.getObject(rs,context.getFieldMatcher(),prefix));
      return true;
    }
  }
;
  ing.doLoop(rs,context);
  return ing.getList();
}
