public static DbQuery query(final Connection conn,final String sqlString){
  return new DbQuery(DbOom.get(),conn,sqlString);
}
