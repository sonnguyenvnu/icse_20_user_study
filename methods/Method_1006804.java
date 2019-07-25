@Override public boolean refresh(Object obj){
  Connection conn=null;
  try {
    conn=DataSourceUtil.getConnection();
  }
 catch (  Exception e) {
    throw new RuntimeException("NO CONNECTION");
  }
  return refresh(obj,conn);
}
