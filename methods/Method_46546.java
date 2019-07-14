public long count(){
  String sql="SELECT count(*) FROM TXLCN_LOG";
  return h2DbHelper.query(sql,new ScalarHandler<Long>());
}
