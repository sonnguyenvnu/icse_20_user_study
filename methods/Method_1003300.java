private void open(){
  try {
    conn=JdbcUtils.getConnection(driver,url,user,password);
  }
 catch (  SQLException e) {
    throw DbException.convert(e);
  }
}
