@Override public long create(Object obj){
  Connection conn=null;
  try {
    conn=DataSourceUtil.getConnection();
  }
 catch (  Exception e) {
    throw new RuntimeException("NO CONNECTION");
  }
  return create(obj,conn);
}
